package com.hometask.controller;

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
    public void addCustomer(){
        Customer newCustomer = new Customer();
        Assert.assertTrue(customerCtrl.add(newCustomer));
    }

    @Test
    public void editCustomer(){
        Customer newCustomer = new Customer();
        Assert.assertTrue(customerCtrl.edit(defaultCustomer));
    }

}
