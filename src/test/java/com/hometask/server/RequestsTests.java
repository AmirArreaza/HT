package com.hometask.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hometask.controller.CustomerCtrl;
import com.hometask.model.Account;
import com.hometask.model.adapter.AccountAdapter;
import com.hometask.model.Customer;
import com.hometask.service.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class RequestsTests{

    private static final CustomerCtrl customerCtrl = new CustomerCtrl();
    private GsonBuilder gsonBilder;
    private Gson gson;
    private TypeToken<List<Customer>> customerToken;
    private TypeToken<List<Account>> accountToken;
    Server server;

    @Before
    public void prepareServer(){
        server = new Server();
        gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(Account.class, new AccountAdapter());
        gson = gsonBilder.create();
        customerToken = new TypeToken<List<Customer>>() {};
        accountToken = new TypeToken<List<Account>>(){};
    }

    @Test
    public void getAllUsersRequest(){
        String[] body = server.executeService("/Customers", "GET");

        List<Customer> customers = gson.fromJson(body[1], customerToken.getType());
        ArrayList<String> customerFromMemory = new ArrayList<>();
        customerCtrl.inMemoryCustomers.forEach((k,v)->{
            customerFromMemory.add(v.getId().toString());
        });

        ArrayList<String> customerRequest = new ArrayList<>();
        customers.forEach((k)->{
            customerRequest.add(k.getId().toString());
        });

        String[] customerRequestArray = customerRequest.toArray(new String[0]);
        String[] customerFromMemoryArray = customerFromMemory.toArray(new String[0]);
        Assert.assertArrayEquals(customerFromMemoryArray, customerRequestArray);
    }

    @Test
    public void getCustomerRequest(){
        Customer customer = customerCtrl.inMemoryCustomers.firstEntry().getValue();
        String[] conn = server.executeService("/Customer/"  + customer.getId().toString(), "GET");
        Customer rtnCustomer = gson.fromJson(conn[1], Customer.class);
        Assert.assertEquals(conn[0], "200");
        Assert.assertEquals( customer.getId().toString(), rtnCustomer.getId().toString());

        conn = server.executeService("/Customer/"  + "xxx", "GET");
        Assert.assertNotEquals(conn[0], "200");
        Assert.assertEquals(conn[0], "400");
    }

    @Test
    public void transferMoneyRequest(){

        Customer customer = customerCtrl.inMemoryCustomers.firstEntry().getValue();
        String[] conn = server.executeService("/CustomerLogIn/"  + customer.getId().toString(), "GET");

        Assert.assertEquals(conn[0], "200");
        System.out.println("Customer " + customer.getFirstName() + " logged in");

        String accountNumber1 = customer.getAccounts().get(0).getAccountNumber();
        String accountNumber2 = customer.getAccounts().get(1).getAccountNumber();
        String route = "/Customer/Transfer/" + accountNumber1 + "/" + accountNumber2 + "/" + "50";
        conn = server.executeService(route,"GET");

        Assert.assertEquals("200", conn[0]);
        String result = "success";
        Assert.assertEquals(result, conn[1]);

        route = "/Customer/Transfer/" + accountNumber1 + "/" + accountNumber2 + "/" + "9999999999";
        conn = server.executeService(route,"GET");
        Assert.assertEquals("200", conn[0]);
        Assert.assertEquals("Not Enough Money", conn[1]);
    }

    @Test
    public void getAllAccountsFromUser(){
        Customer customer = customerCtrl.inMemoryCustomers.firstEntry().getValue();
        String[] conn = server.executeService("/CustomerLogIn/"  + customer.getId().toString(), "GET");

        Assert.assertEquals(conn[0], "200");
        System.out.println("Customer " + customer.getFirstName() + " logged in");

        String[] body = server.executeService("/Accounts", "GET");
        List<Account> accounts = gson.fromJson(body[1], accountToken.getType());

        Assert.assertEquals(body[0], "200");
        Assert.assertEquals(5, accounts.size());
    }
}