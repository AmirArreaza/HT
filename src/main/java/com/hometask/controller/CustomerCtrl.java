package com.hometask.controller;

import com.hometask.model.AccBusiness;
import com.hometask.model.AccPersonal;
import com.hometask.model.Address;
import com.hometask.model.Customer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CustomerCtrl implements Controller<Customer>{

    public static final TreeMap<String,Customer> inMemoryCustomers = new TreeMap<>();

    public CustomerCtrl(){
        populateCustomers();
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
    public List<Customer> getAll() {
        return new ArrayList<>(inMemoryCustomers.values());
    }

    public boolean transferMoney(AccPersonal account1, AccPersonal account2, double amount) {

        System.out.println(String.format("Transfering {3}{0} from account {1} to account {2}",
                                                                                    amount,
                                                                                    account1.getAccountNumber(),
                                                                                    account2.getAccountNumber(),
                                                                                    account1.getCurrency()));
        BigDecimal amountToTransfer = BigDecimal.valueOf(amount).setScale(2);
        if(account1.getBalance().compareTo(amountToTransfer) <= 0){
            System.out.println("Not enough money on account");
            return false;
        }
        account1.deductBalance(amount);
        account2.addBalance(amount);

        return true;
    }

    private void populateCustomers() {
        Address defaultAddress = new Address("HA9 0GE");

        AccPersonal personal1 = new AccPersonal("12345","00-00-00");
        personal1.addBalance(100.00);
        personal1.settype(AccPersonal.personalTypes.METAL);
        AccPersonal personal2 = new AccPersonal("23456","00-00-00");
        personal2.addBalance(123213.30);
        personal2.settype(AccPersonal.personalTypes.METAL);
        AccPersonal personal3 = new AccPersonal("34567","00-00-00");
        personal3.addBalance(30.00);
        personal3.settype(AccPersonal.personalTypes.METAL);
        AccPersonal personal4 = new AccPersonal("45678","00-00-00");
        personal4.addBalance(450.00);
        personal4.settype(AccPersonal.personalTypes.METAL);
        AccBusiness personal5 = new AccBusiness("56789","00-00-00", AccBusiness.businessType.FREELANCER);
        personal5.addBalance(10.00);
        personal5.setPricing(String.valueOf(AccBusiness.pricingFreelancer.PROFESSIONAL));

        Customer customer1 = new Customer();
        customer1.setFirstName("Customer 1");
        customer1.setAddress(defaultAddress);
        Customer customer2 = new Customer();
        customer2.setFirstName("Customer 2");
        customer2.setAddress(defaultAddress);
        Customer customer3 = new Customer();
        customer3.setFirstName("Customer 3");
        customer3.setAddress(defaultAddress);
        Customer customer4 = new Customer();
        customer4.setFirstName("Customer 4");
        customer4.setAddress(defaultAddress);
        Customer customer5 = new Customer();
        customer5.setFirstName("Customer 5");
        customer5.setAddress(defaultAddress);

        customer1.addAccount(personal1);
        customer2.addAccount(personal2);
        customer3.addAccount(personal3);
        customer4.addAccount(personal4);
        customer5.addAccount(personal5);

        inMemoryCustomers.put(customer1.getId().toString(), customer1);
        inMemoryCustomers.put(customer2.getId().toString(), customer2);
        inMemoryCustomers.put(customer3.getId().toString(), customer3);
        inMemoryCustomers.put(customer4.getId().toString(), customer4);
        inMemoryCustomers.put(customer5.getId().toString(), customer5);
    }

}
