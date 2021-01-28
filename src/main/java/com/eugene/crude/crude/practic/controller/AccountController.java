package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.Account;

import com.eugene.crude.crude.practic.repository.AccountRepository;

import com.eugene.crude.crude.practic.repository.hibernate.AccountRepositoryImpl;


import java.sql.SQLException;
import java.util.List;

public class AccountController {
    AccountRepository postRepository;


    public AccountController() {

        this.postRepository = new AccountRepositoryImpl();
    }

    public Account save(Account post) {

        post = postRepository.save(post);
        if (post == null)
            return null;
        else
            return post;
    }

    public void deleteById(String str) {

        postRepository.deleteById(Integer.parseInt(str));
    }

    public Account getElementById(String str) {

        Account post = null;
        try {
            post = postRepository.getById(Integer.parseInt(str));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (post == null)
            return null;
        else {
            return post;
        }
    }

    public Account update(Account post) {

        post = postRepository.update(post);
        if (post != null) {
            return post;
        } else return null;


    }

    public List<Account> getAll() {

        List<Account> postList = null;
        try {
            postList = postRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (postList == null)
            return null;
        else
            return postList;
    }

}
