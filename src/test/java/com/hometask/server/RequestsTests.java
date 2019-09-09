package com.hometask.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hometask.controller.AccountCtrl;
import com.hometask.controller.CustomerCtrl;
import com.hometask.model.Account;
import com.hometask.model.adapter.AccountAdapter;
import com.hometask.model.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import spark.Response;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static spark.Spark.*;

public class RequestsTests{

    private static final CustomerCtrl customerCtrl = new CustomerCtrl();
    private GsonBuilder gsonBilder;
    private Gson gson;
    private TypeToken<List<Customer>> token;
    Server server;

    @Before
    public void prepareServer(){
        server = new Server();
        gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(Account.class, new AccountAdapter());
        gson = gsonBilder.create();
        token = new TypeToken<List<Customer>>() {};
    }

    @Test
    public void getAllUsersRequest(){
        String[] body = server.executeService("/Customers", "GET");

        List<Customer> customers = gson.fromJson(body[1], token.getType());
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



    }

}