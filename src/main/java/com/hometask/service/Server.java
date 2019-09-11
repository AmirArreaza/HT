package com.hometask.service;

import com.google.gson.Gson;
import com.hometask.controller.AccountCtrl;
import com.hometask.controller.CustomerCtrl;
import com.hometask.model.AccPersonal;
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
        get("/CustomerLogIn/:id", (req, res) -> {
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
        get("/Customer/Transfer/:acc1/:acc2/:amount",(req, res) -> {
            AccPersonal account1 = (AccPersonal) customerCtrl.getAccount(req.params("acc1"));
            AccPersonal account2 = (AccPersonal) customerCtrl.getAccount(req.params("acc2"));
            if(account1 != null && account2 != null){
                Double amount = Double.parseDouble(req.params("amount"));

                switch (customerCtrl.transferMoney(account1, account2, amount)){
                    case SUCCESS:
                        res.body("success");
                        break;
                    case NOT_ENOUGH_MONEY:
                        res.body("Not Enough Money");
                        break;
                    case ERROR:
                        res.body("Unknown error");
                        break;
                    default:
                        res.body("Error");
                        break;
                }
                res.status(200);
            }else{
                res.status(400);
                res.body("Wrong Parameters");
            }
            return res.body();
        });
        get("/Accounts",(req, res) ->{
            res.type("application/json");
            return new Gson().toJson(accountCtrl.getAll());
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
                response[1] = "Error";
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            return response;
        }
    }

}
