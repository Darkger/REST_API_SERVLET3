package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.Post;

import java.io.IOException;
import java.util.List;

public interface Controller  <T>{
    public T save(T post) throws IOException ;

    public void deleteById(String str) throws IOException ;

    public T getElementById(String str) throws IOException;

    public T update(T post) throws IOException ;

    public List<T> getAll() throws IOException ;
}
