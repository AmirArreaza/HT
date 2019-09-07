package com.hometask.model;

public class AccPersonal extends Account {

    public enum personalTypes{
        STANDARD,
        PREMIUM,
        METAL
    }

    private personalTypes type;

    /**
     *
     * @param accountNumber the bank account number
     * @param sortCode the sort code
     */
    public AccPersonal(String accountNumber, String sortCode){
        this.setAccountNumber(accountNumber);
        this.setSortCode(sortCode);
    }

    /**
     *
     * @return the type of the personal account
     */
    public personalTypes gettype() {
        return type;
    }

    /**
     *
     * @param type Personal account type
     */
    public void settype(personalTypes type) {
        this.type = type;
    }

}
