package com.hometask.server;

import com.google.gson.Gson;
import com.hometask.controller.CustomerCtrl;
import com.hometask.model.Customer;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static spark.Spark.*;



public class Server {

    CustomerCtrl customerCtrl = new CustomerCtrl();

    public Server(){
        init();
        get("/hello", (req, res) -> "Hello");
        get("/Customers", (req, res) -> {
            res.type("application/json");
            res.body();
            return new Gson().toJson(customerCtrl.getAll());
        });
        get("/Customer/:id", (req, res) -> {
            res.type("application/json");
            Customer customer = customerCtrl.get(req.params("id"));
            if(customer != null){
                res.body(new Gson().toJson(customer));
                res.status(200);
            }else{
                res.status(400);
            }
            return new Gson().toJson(customer);});

        /**
         * REST webservice to transfer money between accounts
         *  User id
         *  Account origin
         *  Account dest
         *  amount
         */
        post("/Customer/:id/:acc1/:acc2/:amount",(req, res) -> "");
    }

    public String[] executeService(String route, String method){
        String[] response = new String[2];
        HttpURLConnection connection;
        try {
            URL url = new URL("http://localhost:4567" + route);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            response[0] = String.valueOf(connection.getResponseCode());
            if(response[0] == "200"){
                response[1] = "400 Bad Request";
            }else{
                response[1] = IOUtils.toString(connection.getInputStream());
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            return response;
        }
    }

}
