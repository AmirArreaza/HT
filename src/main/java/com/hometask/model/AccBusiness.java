package com.hometask.model;

public class AccBusiness extends Account {

    /**
     * Business Account types
     */
    public enum businessType{
        COMPANY,
        FREELANCER
    }

    /**
     * Pricing types for a company account
     */
    public enum pricingCompany{
        FREE,
        GROW,
        SCALE,
        ENTERPRISE
    }

    /**
     * Pricing types for a freelance account
     */
    public enum pricingFreelancer{
        FREE,
        PROFESSIONAL
    }

    /**
     * Account type
     */
    private businessType type;

    /**
     * Pricing of the account
     */
    private String pricing;

    /**
     *
     * @param accountNumber account number to set
     * @param sortCode sort code of the account to set
     * @param type customer account type to set
     */
    public AccBusiness(String accountNumber, String sortCode, businessType type){
        this.setAccountNumber(accountNumber);
        this.setSortCode(sortCode);
        this.type = type;
    }

    /**
     *
     * @return the account type
     */
    public businessType getcustomerType() {
        return type;
    }

    /**
     *
     * @param customerType Customer account type to set
     */
    public void setcustomerType(businessType customerType) {
        this.type = customerType;
    }

    /**
     *
     * @return the pricing for the account
     */
    public String getPricing() {
        return pricing;
    }

    /**
     *
     * @param pricing pricing of the account to set
     */
    public void setPricing(String pricing) {
        this.pricing = pricing;
    }
}
