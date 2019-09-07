package com.hometask.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTests {

    @SuppressWarnings("FieldCanBeLocal")
    private Address defaultAddress;

    @Before
    public void populateDefualtValues(){
        defaultAddress = new Address("HA9 0GE");
        defaultAddress.setName("Flat 89 Marathon House");
        defaultAddress.setStreet("33 Olympic way");
        defaultAddress.setPostTown("Wembley");
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


}
