package com.hometask.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTests {

    @SuppressWarnings("FieldCanBeLocal")
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
        personalAccount.addBalance(20.5611111);
        personalAccount.setCurrency('£');

        Assert.assertEquals("0123456789", personalAccount.getAccountNumber());
        Assert.assertEquals("00-21-30", personalAccount.getSortCode());
        Assert.assertEquals("HA9 0GE", personalAccount.getBankAddress().getPostCode());
        Assert.assertEquals("REVOGB21", personalAccount.getBankIdentifierCode());

        Assert.assertEquals(AccPersonal.personalTypes.METAL, personalAccount.gettype());
        Assert.assertEquals(20.56, personalAccount.getBalance(), 0);
        Assert.assertEquals('£', personalAccount.getCurrency());

    }

    @Test
    public void createBusinessAccount(){

        AccBusiness companyAccount = new AccBusiness("0123456789","00-21-30", AccBusiness.businessType.COMPANY);
        AccBusiness freelancerAccount = new AccBusiness("","", AccBusiness.businessType.FREELANCER);

        companyAccount.setBankIdentifierCode("REVOGB21");
        companyAccount.setBankAddress(defaultAddress);
        companyAccount.setPricing(String.valueOf(AccBusiness.pricingCompany.ENTERPRISE));
        companyAccount.addBalance(20.5611111);
        companyAccount.setCurrency('£');

        freelancerAccount.setAccountNumber("0123456789");
        freelancerAccount.setSortCode("00-21-30");
        freelancerAccount.setBankIdentifierCode("REVOGB21");
        freelancerAccount.setBankAddress(defaultAddress);
        freelancerAccount.setPricing(String.valueOf(AccBusiness.pricingFreelancer.PROFESSIONAL));
        freelancerAccount.addBalance(20.5611111);
        freelancerAccount.setCurrency('£');

        // Company Account tests
        Assert.assertEquals("0123456789", companyAccount.getAccountNumber());
        Assert.assertEquals("00-21-30", companyAccount.getSortCode());
        Assert.assertEquals("HA9 0GE", companyAccount.getBankAddress().getPostCode());
        Assert.assertEquals("REVOGB21", companyAccount.getBankIdentifierCode());

        Assert.assertEquals(AccBusiness.businessType.COMPANY, companyAccount.getcustomerType());
        Assert.assertEquals(20.56, companyAccount.getBalance(), 0);
        Assert.assertEquals('£', companyAccount.getCurrency());
        Assert.assertEquals(String.valueOf(AccBusiness.pricingCompany.ENTERPRISE), companyAccount.getPricing());

        // Freelancer account tests
        Assert.assertEquals("0123456789", freelancerAccount.getAccountNumber());
        Assert.assertEquals("00-21-30", freelancerAccount.getSortCode());
        Assert.assertEquals("HA9 0GE", freelancerAccount.getBankAddress().getPostCode());
        Assert.assertEquals("REVOGB21", freelancerAccount.getBankIdentifierCode());

        Assert.assertEquals(AccBusiness.businessType.FREELANCER, freelancerAccount.getcustomerType());
        Assert.assertEquals(20.56, freelancerAccount.getBalance(), 0);
        Assert.assertEquals('£', freelancerAccount.getCurrency());
        Assert.assertEquals(String.valueOf(AccBusiness.pricingFreelancer.PROFESSIONAL), freelancerAccount.getPricing());

        freelancerAccount.deductBalance(0.56);
        Assert.assertEquals(20.00, freelancerAccount.getBalance(), 0);

        freelancerAccount.addBalance(100.00);
        Assert.assertEquals(120.00, freelancerAccount.getBalance(), 0);


    }

}
