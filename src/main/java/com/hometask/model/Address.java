package com.hometask.model;

public class Address {

    /**
     * Building or house name
     */
    private String name;

    /**
     * Street name
     */
    private String street;

    /**
     * Dependent Locality (large villages)
     */
    private String dependentLocality;

    /**
     * Post town name
     */
    private String postTown;

    /**
     * Post Code
     */
    private String postCode;

    public Address(String postCode){
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDependentLocality() {
        return dependentLocality;
    }

    public void setDependentLocality(String dependentLocality) {
        this.dependentLocality = dependentLocality;
    }

    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
