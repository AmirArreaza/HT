package com.hometask.model;

public abstract class Account {

    /**
     * AccountNumber as a String to prevent to ignore an account number that start with a '0'
     */
    private String accountNumber;

    /**
     * Bank Sort Code
     */
    private String sortCode;

    /**
     * Bank identifier code
     */
    private String bankIdentifierCode;

    /**
     * Bank main addresss
     */
    private Address bankAddress;

    /**
     *  @return the account number
     * */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     *
     * @param accountNumber account number to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     *
     * @return the sort code
     */
    public String getSortCode() {
        return sortCode;
    }

    /**
     *
     * @param sortCode bank sort code
     */
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     *
     * @return the bank identifier code
     */
    public String getBankIdentifierCode() {
        return bankIdentifierCode;
    }

    /**
     *
     * @param bankIdentifierCode bank identifier code to set
     */
    public void setBankIdentifierCode(String bankIdentifierCode) {
        this.bankIdentifierCode = bankIdentifierCode;
    }

    /**
     *
     * @return the bank address
     */
    public Address getBankAddress() {
        return bankAddress;
    }

    /**
     *
     * @param bankAddress bak address to set
     */
    public void setBankAddress(Address bankAddress) {
        this.bankAddress = bankAddress;
    }
}
