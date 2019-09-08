package com.hometask.controller;

import com.hometask.model.Customer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CustomerCtrl implements Controller<Customer>{

    @Override
    public boolean add(Customer customer) {
        throw new NotImplementedException();
    }

    @Override
    public boolean edit(Customer customer) {
        throw new NotImplementedException();
    }
}
