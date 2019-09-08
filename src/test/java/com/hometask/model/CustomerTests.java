package com.hometask.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTests {

    @SuppressWarnings("FieldCanBeLocal")
    private Address defaultAddress;
    private AccPersonal defaultAccount;
    private Customer defaultCustomer;

    @Before
    public void populateDefualtValues(){
        defaultAddress = new Address("HA9 0GE");
        defaultAddress.setName("Flat 89 Marathon House");
        defaultAddress.setStreet("33 Olympic way");
        defaultAddress.setPostTown("Wembley");

        defaultAccount = new AccPersonal("0123456789", "00-00-00");
        defaultAccount.setBankIdentifierCode("REVOGB21");
        defaultAccount.setBankAddress(defaultAddress);
        defaultAccount.settype(AccPersonal.personalTypes.METAL);
        defaultAccount.setBalance(20.5611111);
        defaultAccount.setCurrency('£');

        defaultCustomer = new Customer();
        defaultCustomer.setFirstName("Amir");
        defaultCustomer.setLastName("Arreaza");
        defaultCustomer.setEmail("ajaarreazav@gmail.com");
        defaultCustomer.setMobile("07380189406");
        defaultCustomer.setAddress(defaultAddress);

    }

    @Test
    public void createCustomer(){
        Customer customer = new Customer();

        customer.setFirstName("Amir");
        customer.setLastName("Arreaza");
        customer.setEmail("ajaarreazav@gmail.com");
        customer.setMobile("07380189406");
        customer.setAddress(defaultAddress);

        Assert.assertEquals("Arreaza", customer.getLastName());
        Assert.assertEquals("Amir", customer.getFirstName());
        Assert.assertEquals("ajaarreazav@gmail.com", customer.getEmail());
        Assert.assertEquals("07380189406", customer.getMobile());
        Assert.assertEquals("HA9 0GE", customer.getAddress().getPostCode());
    }

    @Test
    public void addAccountToCustomer(){
        defaultCustomer.addAccount(defaultAccount);
        Assert.assertEquals(defaultCustomer.getAccounts().get(0).getAccountNumber(),
                            defaultAccount.getAccountNumber());
    }

}
