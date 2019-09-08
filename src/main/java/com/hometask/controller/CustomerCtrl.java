package com.hometask.controller;

import com.hometask.model.AccPersonal;
import com.hometask.model.Customer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileWriter;
import java.io.IOException;

public class CustomerCtrl implements Controller<Customer>{

    @Override
    public boolean add(Customer customer) {
        try{

            //if(customer.getAccounts().size() <= 0) return false;

            gson.toJson(customer, new FileWriter("Customer.data"));

            return true;
        }catch(IOException ex){
            return false;
        }
    }

    @Override
    public boolean edit(Customer customer) {
        throw new NotImplementedException();
    }

    public boolean transferMoney(AccPersonal account1, AccPersonal account2) {

        throw new NotImplementedException();

    }
}
