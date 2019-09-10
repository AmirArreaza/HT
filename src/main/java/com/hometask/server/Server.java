package com.hometask.server;

import com.google.gson.Gson;
import com.hometask.controller.AccountCtrl;
import com.hometask.controller.CustomerCtrl;
import com.hometask.model.AccPersonal;
import com.hometask.model.Account;
import com.hometask.model.Customer;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static spark.Spark.*;



public class Server {

    CustomerCtrl customerCtrl = new CustomerCtrl();
    AccountCtrl accountCtrl = new AccountCtrl();

    public Server(){
        init();
        loadRoutes();
    }

    private void loadRoutes(){
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
            return new Gson().toJson(customer);
        });
        put("/CustomerLogIn/:id", (req, res) -> {
            req.session(true);
            res.type("application/json");
            Customer customer = customerCtrl.get(req.params("id"));
            if(customerCtrl.logIn(customer.getId().toString())){
                res.body(new Gson().toJson("success"));
                res.status(200);
            }else{
                res.status(400);
            }
            return res.body();
        });
        /**
         * REST webservice to transfer money between accounts
         *  User id
         *  Account origin
         *  Account dest
         *  amount
         */
        put("/Customer/Transfer/:acc1/:acc2/:amount",(req, res) -> {

            AccPersonal account1 = (AccPersonal) accountCtrl.get(req.params("acc1"));
            AccPersonal account2 = (AccPersonal) accountCtrl.get(req.params("acc2"));

            if(account1 != null && account2 != null){
                CustomerCtrl customerCtrl = new CustomerCtrl();
                Double amount = Double.parseDouble(req.params("amount"));
                if(customerCtrl.transferMoney(account1, account2, amount)){
                    res.status(200);
                    res.body(new Gson().toJson("success"));
                }else{
                    res.status(400);
                }
            }else{
                res.status(400);
            }
            return res.body();
        });
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
            if(response[0].equals("200")){
                response[1] = IOUtils.toString(connection.getInputStream());
            }else{
                response[1] = "400 Bad Request";
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            return response;
        }
    }

}
