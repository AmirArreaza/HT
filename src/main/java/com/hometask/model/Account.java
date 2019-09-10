package com.hometask.model;

import java.math.BigDecimal;

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
     * The acocunt balance
     */
    private BigDecimal balance = new BigDecimal(0);

    /**
     * Account currency
     */
    private char currency;

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

    /**
     *
     * @return account balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     *
     * @param amount to add to the balance
     */
    public void addBalance(double amount) {
        this.balance = this.balance.add(BigDecimal.valueOf(amount).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    }

    /**
     *
     * @param amount to deduct from the balance
     */
    public void deductBalance(double amount) {
        this.balance = this.balance.add(BigDecimal.valueOf((-1) * amount).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    }

    /**
     *
     * @return account currency
     */
    public char getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency account currency to set
     */
    public void setCurrency(char currency) {
        this.currency = currency;
    }

    @Override
    public String toString(){
        return "Account " + this.accountNumber + " " +
                "Current Balance " + "£" + this.balance;
    }
}
