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

    public boolean transferMoney(AccPersonal account1, AccPersonal account2, double amount) {

        System.out.println(String.format("Transfering {0} from account {1} to account {2}",
                                                                                    amount,
                                                                                    account1.getAccountNumber(),
                                                                                    account2.getAccountNumber()));
        if(account1.getBalance() < amount){
            System.out.println("Not enough money on account");
            return false;
        }

        account1.deductBalance(amount);
        account2.addBalance(amount);


        throw new NotImplementedException();

    }
}
