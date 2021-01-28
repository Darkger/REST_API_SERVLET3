package com.eugene.crude.crude.practic.repository.hibernate;


import com.eugene.crude.crude.practic.model.Account;
import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.model.builder.builderImpl.AccountBuilderImpl;
import com.eugene.crude.crude.practic.repository.AccountRepository;
import com.eugene.crude.crude.practic.utils.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;


public class AccountRepositoryImpl implements AccountRepository {




    public AccountRepositoryImpl( ) {

    }

    @Override
    public Account getById(Integer aLong)  {
        List<Account> listPost = new ArrayList<>();
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            session.beginTransaction();
          listPost.set(0,session.get(Account.class,aLong));
        }


        return new AccountBuilderImpl(listPost.get(0).getId(), listPost.get(0).getUser(),listPost.get(0).getAccountStatus(),listPost.get(0).getDate()).build();
    }

    @Override
    public List<Account> getAll() {
        List<Account> listPost;
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
          session.beginTransaction();
            listPost= session.createQuery("FROM Account ").list();



        }
        return listPost;

    }

    @Override
    public Account save(Account account) {
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        }



        return account;
    }

    @Override
    public Account update(Account file) {
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.update(file);
            transaction.commit();
            session.close();
        }
        return file;
    }

    @Override
    public void deleteById(Integer aLong) {

        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();

            session.delete(session.get(File.class,aLong));
            transaction.commit();
        }
        }
    }
