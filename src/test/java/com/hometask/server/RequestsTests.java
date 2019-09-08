package com.hometask.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hometask.controller.CustomerCtrl;
import com.hometask.model.Account;
import com.hometask.model.AccountAdapter;
import com.hometask.model.Customer;
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
import static spark.Spark.get;
import static spark.Spark.init;

public class RequestsTests{

    private static final CustomerCtrl customerCtrl = new CustomerCtrl();

    @Before
    public void populateDefaultValues(){

    }

    @Test
    public void requestsTests(){
        server();
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(Account.class, new AccountAdapter());
        Gson gson = gsonBilder.create();
        TypeToken<List<Customer>> token = new TypeToken<List<Customer>>() {};

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

    static void server(){
        init();
        get("/hello", (req, res) -> "Hello XXX");
        get("/Customers", (req, res) -> {
            res.type("application/json");
            res.body();
            return new Gson().toJson(customerCtrl.getAll());
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

}
