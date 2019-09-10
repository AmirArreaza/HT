package com.hometask.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hometask.model.Account;
import com.hometask.model.Customer;
import com.hometask.model.adapter.AccountAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private GsonBuilder gsonBilder;
    private Gson gson;
    private TypeToken<List<Customer>> token;

    public CustomerService(){
        gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(Account .class, new AccountAdapter());
        gson = gsonBilder.create();
        token = new TypeToken<List<Customer>>() {};
    }

    public List<Customer> getAllCustomers(String response[]){

        if(response[0].equals("200")){
            return  gson.fromJson(response[1], token.getType());
        }else{
            System.out.println("Error " + response[1]);
            return new ArrayList<>();
        }
    }

    public Customer getCustomer(String response[]){
        if(response[0].equals("200")){
            return  gson.fromJson(response[1], Customer.class);
        }else{
            System.out.println("Error " + response[1]);
            return new Customer();
        }
    }

    public boolean logIn(String[] response) {
        if(response[0].equals("200")){
            return true;
        }else{
            System.out.println("Error " + response[1]);
            return false;
        }
    }
}
