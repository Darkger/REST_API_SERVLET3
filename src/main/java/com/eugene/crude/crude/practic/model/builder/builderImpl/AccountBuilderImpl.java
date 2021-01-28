package com.eugene.crude.crude.practic.model.builder.builderImpl;

import com.eugene.crude.crude.practic.model.*;
import com.eugene.crude.crude.practic.model.builder.Builder;

import javax.persistence.*;
import java.util.Date;

public class AccountBuilderImpl implements Builder {

    private int id;
    private User user;
    private AccountStatus accountStatus;
    private Date date;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public AccountBuilderImpl setUser(User user) {
        this.user = user;
        return this;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public AccountBuilderImpl setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public AccountBuilderImpl setDate(Date date) {
        this.date = date;
        return this;
    }


    public AccountBuilderImpl setId(int id) {
        this.id = id;
        return this;
    }

    public AccountBuilderImpl(int id, User user, AccountStatus accountStatus, Date date) {
        this.id = id;
        this.user = user;
        this.accountStatus = accountStatus;
        this.date = date;
    }

    public Account build(){
        return  new Account(this);
    }
}
