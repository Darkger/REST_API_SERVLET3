package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.builder.builderImpl.AccountBuilderImpl;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date date;

    @Column(name = "status_id")
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Account(User user, Date date, AccountStatus accountStatus) {

        this.user = user;
        this.date = date;
        this.accountStatus = accountStatus;
    }

    public Account(AccountBuilderImpl accountBuilder) {

        this.user = accountBuilder.getUser();
        this.accountStatus = accountBuilder.getAccountStatus();
        this.date = accountBuilder.getDate();
    }


}
