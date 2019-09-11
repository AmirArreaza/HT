package com.hometask.controller;

import com.hometask.model.AccBusiness;
import com.hometask.model.AccPersonal;
import com.hometask.model.Account;

import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class AccountCtrl implements Controller<Account>{

    public static final TreeMap<String, Account> inMemoryAccounts = new TreeMap<>();

    public AccountCtrl(){populateAccount();}

    @Override
    public boolean add(Account account) {
        return false;
    }

    @Override
    public boolean edit(Account account) {
        return false;
    }

    @Override
    public Account get(String number) {
        return inMemoryAccounts.get(number);
    }

    @Override
    public List<Account> getAll() {
        return CustomerCtrl.inMemoryLoggedCustomer.getAccounts();
    }

    public static void populateAccount(){
        AccPersonal personal1 = new AccPersonal("123456","00-00-00");
        personal1.addBalance(100.00);
        personal1.settype(AccPersonal.personalTypes.METAL);
        AccPersonal personal2 = new AccPersonal("234567","00-00-00");
        personal2.addBalance(123213.30);
        personal2.settype(AccPersonal.personalTypes.METAL);
        AccPersonal personal3 = new AccPersonal("345678","00-00-00");
        personal3.addBalance(30.00);
        personal3.settype(AccPersonal.personalTypes.METAL);
        AccPersonal personal4 = new AccPersonal("456789","00-00-00");
        personal4.addBalance(450.00);
        personal4.settype(AccPersonal.personalTypes.METAL);
        AccBusiness personal5 = new AccBusiness("567890","00-00-00", AccBusiness.businessType.FREELANCER);
        personal5.addBalance(10.00);
        personal5.setPricing(String.valueOf(AccBusiness.pricingFreelancer.PROFESSIONAL));

        inMemoryAccounts.put(personal1.getAccountNumber(), personal1);
        inMemoryAccounts.put(personal2.getAccountNumber(), personal2);
        inMemoryAccounts.put(personal3.getAccountNumber(), personal3);
        inMemoryAccounts.put(personal4.getAccountNumber(), personal4);
        inMemoryAccounts.put(personal5.getAccountNumber(), personal5);

    }
}
