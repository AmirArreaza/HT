package com.hometask.controller;

import com.hometask.model.AccPersonal;
import com.hometask.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerCtrlTests {

    private CustomerCtrl customerCtrl = new CustomerCtrl();
    private Customer defaultCustomer;

    @Before
    public void populateDefaultData(){
        defaultCustomer = new Customer();
        defaultCustomer.setFirstName("Amir");
        defaultCustomer.setLastName("Arreaza");
        defaultCustomer.setEmail("ajaarreazav@gmail.com");
        defaultCustomer.setMobile("07380189406");
        //defaultCustomer.setAddress(defaultAddress);
    }

    @Test
    public void transferMoney(){

        AccPersonal account1 = new AccPersonal("","");
        AccPersonal account2 = new AccPersonal("","");
        Assert.assertTrue(customerCtrl.transferMoney(account1, account2));


    }

    @Test
    public void addCustomer(){
        Assert.assertTrue(customerCtrl.add(defaultCustomer));
    }

    @Test
    public void editCustomer(){
        Customer newCustomer = new Customer();
        Assert.assertTrue(customerCtrl.edit(defaultCustomer));
    }

}
