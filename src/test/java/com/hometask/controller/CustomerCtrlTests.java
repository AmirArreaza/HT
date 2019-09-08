package com.hometask.controller;

import com.hometask.model.AccPersonal;
import com.hometask.model.Address;
import com.hometask.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerCtrlTests {

    private CustomerCtrl customerCtrl = new CustomerCtrl();
    private Customer defaultCustomer;
    private AccPersonal defaultAccount;
    private Address defaultAddress = new Address("HA9 0GE");

    @Before
    public void populateDefaultData(){
        defaultCustomer = new Customer();
        defaultCustomer.setFirstName("Amir");
        defaultCustomer.setLastName("Arreaza");
        defaultCustomer.setEmail("ajaarreazav@gmail.com");
        defaultCustomer.setMobile("07380189406");
        defaultCustomer.setAddress(defaultAddress);
    }

    @Test
    public void transferMoney(){
        double amount = 100.00;

        AccPersonal account1 = new AccPersonal("","");
        AccPersonal account2 = new AccPersonal("","");

        account1 = new AccPersonal("0123456789", "00-00-00");
        account1.setBankIdentifierCode("REVOGB21");
        account1.setBankAddress(defaultAddress);
        account1.settype(AccPersonal.personalTypes.METAL);
        account1.addBalance(200.00);
        account1.setCurrency('£');

        account2 = new AccPersonal("9876543210", "00-00-00");
        account2.setBankIdentifierCode("REVOGB21");
        account2.setBankAddress(defaultAddress);
        account2.settype(AccPersonal.personalTypes.METAL);
        account2.addBalance(100.00);
        account2.setCurrency('£');

        Assert.assertTrue(customerCtrl.transferMoney(account1, account2, amount));
        Assert.assertEquals("200.00", account2.getBalance().toString());
        Assert.assertEquals("100.00", account1.getBalance().toString());
        Assert.assertNotEquals("200.00", account1.getBalance().toString());
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
