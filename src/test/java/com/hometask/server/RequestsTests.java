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
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static spark.Spark.*;

public class RequestsTests{

    private static final AccountCtrl accountCtrl = new AccountCtrl();
    private static final CustomerCtrl customerCtrl = new CustomerCtrl();
    private GsonBuilder gsonBilder;
    private Gson gson;
    private TypeToken<List<Customer>> token;

    static void server(){
        init();
        get("/hello", (req, res) -> "Hello");
        get("/Customers", (req, res) -> {
            res.type("application/json");
            res.body();
            return new Gson().toJson(customerCtrl.getAll());
        });
        get("/Customer/:id", (req, res) -> {
            res.type("application/json");
            res.body();
            return new Gson().toJson(customerCtrl.get(req.params("id")));
            });
    }

    private String getBody(String route, String method){
        try {
            URL url = new URL("http://localhost:4567" + route);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            return IOUtils.toString(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return "";
        }
    }

    @Before
    public void prepareServer(){
        server();
        gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(Account.class, new AccountAdapter());
        gson = gsonBilder.create();
        token = new TypeToken<List<Customer>>() {};
    }

    @After
    public void shutdownServer(){
        stop();
    }

    @Test
    public void getAllUsersRequest(){
        String body = getBody("/Customers", "GET");

        List<Customer> customers = gson.fromJson(body, token.getType());
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

        String body = getBody("/Customer/"  + customer.getId().toString(), "GET");

        Customer rtnCustomer = gson.fromJson(body, Customer.class);
        Assert.assertEquals(rtnCustomer.getId(), customer.getId());
    }

    @Test
    public void transferMoneyRequest(){



    }

}