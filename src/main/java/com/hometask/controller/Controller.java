package com.hometask.controller;

import com.google.gson.Gson;

import java.util.List;

public interface Controller<E> {

    public Gson gson = new Gson();

    boolean add(E e);

    boolean edit(E e);

    List<E> getAll();

}
