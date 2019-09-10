package com.hometask.controller;

import com.hometask.model.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CustomerCtrl implements Controller<Customer>{

    public enum transferResponse{
        SUCCESS,
        NOT_ENOUGH_MONEY,
        ERROR
    }

    private static AccountCtrl accountCtrl;

    public static final TreeMap<String,Customer> inMemoryCustomers = new TreeMap<>();
    public static Customer inMemoryLoggedCustomer;

    public CustomerCtrl(){
        accountCtrl = new AccountCtrl();
        populateCustomers();
    }

    private void populateCustomers() {
        Address defaultAddress = new Address("HA9 0GE");

        Customer customer1 = new Customer();
        customer1.setFirstName("Customer 1");
        customer1.setAddress(defaultAddress);
        customer1.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer1.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer1.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer1.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer1.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        AccountCtrl.populateAccount();


        Customer customer2 = new Customer();
        customer2.setFirstName("Customer 2");
        customer2.setAddress(defaultAddress);
        customer2.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer2.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer2.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer2.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer2.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        AccountCtrl.populateAccount();

        Customer customer3 = new Customer();
        customer3.setFirstName("Customer 3");
        customer3.setAddress(defaultAddress);
        customer3.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer3.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer3.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer3.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer3.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        AccountCtrl.populateAccount();

        Customer customer4 = new Customer();
        customer4.setFirstName("Customer 4");
        customer4.setAddress(defaultAddress);
        customer4.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer4.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer4.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer4.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer4.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        AccountCtrl.populateAccount();

        Customer customer5 = new Customer();
        customer5.setFirstName("Customer 5");
        customer5.setAddress(defaultAddress);
        customer5.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer5.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer5.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer5.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        customer5.addAccount(AccountCtrl.inMemoryAccounts.pollFirstEntry().getValue());
        AccountCtrl.populateAccount();

        inMemoryCustomers.put(customer1.getId().toString(), customer1);
        inMemoryCustomers.put(customer2.getId().toString(), customer2);
        inMemoryCustomers.put(customer3.getId().toString(), customer3);
        inMemoryCustomers.put(customer4.getId().toString(), customer4);
        inMemoryCustomers.put(customer5.getId().toString(), customer5);
    }

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

    @Override
    public Customer get(String id) {
        return inMemoryCustomers.get(id);
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(inMemoryCustomers.values());
    }

    public boolean logIn(String custId){
        if(inMemoryCustomers.containsKey(custId)) {
            Customer customerLoggedIn = inMemoryCustomers.get(custId);
            inMemoryLoggedCustomer = customerLoggedIn;
            return true;
        }
        return false;
    }

    public Account getAccount(String accountNumber){
        for(Account account : inMemoryLoggedCustomer.getAccounts()){
            if(account.getAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    public transferResponse transferMoney(AccPersonal account1, AccPersonal account2, double amount) {
        try{
            BigDecimal amountToTransfer = BigDecimal.valueOf(amount).setScale(2);
            if(account1.getBalance().compareTo(amountToTransfer) <= 0){
                return transferResponse.NOT_ENOUGH_MONEY;
            }
            account1.deductBalance(amount);
            account2.addBalance(amount);
            return transferResponse.SUCCESS;
        }catch(Exception ex){
            return transferResponse.ERROR;
        }
    }


}
