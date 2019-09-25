package com.hometask.model;

import java.util.*;

public class Customer extends ResourceSupport{

    /**
     * User unique id
     */
    private UUID id;

    /**
     * First Name
     */
    private String firstName;

    /**
     * Last Name
     */
    private String lastName;

    /**
     * User Address
     */
    private Address address;

    /**
     * User email
     */
    private String email;

    /**
     * User mobile number
     */
    private String mobile;

    /**
     * Account list ordered by account number
     */
    private ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Customer constructor
     */
    public Customer(){
        this.id = UUID.randomUUID();
    }

    /**
     *
     * @return the user id
     */
    public UUID getId() {
        return id;
    }

    /**
     *
     * @return customer first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName customer first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return customer last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName customer last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return the address of the user
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address address of the customer to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email customer email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return customer mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile mobile number to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return List of accounts sorted by account number
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     *
     * @param account account to add to the customer account lists
     */
    public void addAccount(Account account) {
        this.getAccounts().add(account);
    }

    @Override
    public String toString(){
        return this.firstName + " (" + this.id + ")";
    }
}
