package com.hometask.model;

import java.beans.Transient;
import java.text.DecimalFormat;

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
     * Decimal format to allow only two decimals
     */
    private DecimalFormat df2 = new DecimalFormat("#.##");

    /**
     * The acocunt balance
     */
    private transient double balance;

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
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance to set
     */
    public void setBalance(double balance) {
        this.balance = Double.parseDouble(df2.format(balance));
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
}
