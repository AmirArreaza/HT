package com.hometask.controller;

import com.google.gson.Gson;

public interface Controller<E> {

    public Gson gson = new Gson();

    boolean add(E e);

    boolean edit(E e);

}
