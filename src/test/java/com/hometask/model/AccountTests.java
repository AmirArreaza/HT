package com.hometask.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTests {

    Address defaultAddress;

    @Before
    public void populateDefaultValues(){
        defaultAddress = new Address("HA9 0GE");
        defaultAddress.setName("Flat 89 Marathon House");
        defaultAddress.setStreet("33 Olympic way");
        defaultAddress.setPostTown("Wembley");
    }

    @Test
    public void createPersonalAccount(){
        AccPersonal personalAccount = new AccPersonal("","");

        personalAccount.setAccountNumber("0123456789");
        personalAccount.setSortCode("00-21-30");
        personalAccount.setBankIdentifierCode("REVOGB21");
        personalAccount.setBankAddress(defaultAddress);
        personalAccount.settype(AccPersonal.personalTypes.METAL);
        personalAccount.setBalance(20.5611111);
        personalAccount.setCurrency('£');

        Assert.assertEquals("0123456789", personalAccount.getAccountNumber());
        Assert.assertEquals("00-21-30", personalAccount.getSortCode());
        Assert.assertEquals("HA9 0GE", personalAccount.getBankAddress().getPostCode());
        Assert.assertEquals("REVOGB21", personalAccount.getBankIdentifierCode());

        Assert.assertEquals(AccPersonal.personalTypes.METAL, personalAccount.gettype());
        Assert.assertEquals(20.56, personalAccount.getBalance(), 0);
        Assert.assertEquals('£', personalAccount.getCurrency());

    }



}
