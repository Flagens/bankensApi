package com.example.demo.service;

public abstract class BaseService<T> {

    public void process(T entity) {
        System.out.println("siema " + entity.toString());
    }
}
