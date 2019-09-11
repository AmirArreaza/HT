package com.hometask.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hometask.model.Account;
import com.hometask.model.Customer;
import com.hometask.model.adapter.AccountAdapter;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private GsonBuilder gsonBilder;
    private Gson gson;
    private TypeToken<List<Account>> token;

    public AccountService(){
        gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(Account.class, new AccountAdapter());
        gson = gsonBilder.create();
        token = new TypeToken<List<Account>>() {};
    }

    public List<Account> getAllAccounts(String response[]){
        if(response[0].equals("200")){
            return  gson.fromJson(response[1], token.getType());
        }else{
            System.out.println("Error " + response[1]);
            return new ArrayList<>();
        }
    }
}
