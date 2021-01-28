package com.eugene.crude.crude.practic.repository.hibernate;


import com.eugene.crude.crude.practic.model.Account;
import com.eugene.crude.crude.practic.model.Event;
import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.model.builder.builderImpl.AccountBuilderImpl;
import com.eugene.crude.crude.practic.model.builder.builderImpl.EventBuilderImpl;
import com.eugene.crude.crude.practic.repository.AccountRepository;
import com.eugene.crude.crude.practic.repository.EventRepository;
import com.eugene.crude.crude.practic.utils.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {




    public EventRepositoryImpl( ) {

    }

    @Override
    public Event getById(Integer aLong)  {
        List<Event> listPost = new ArrayList<>();
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            session.beginTransaction();
          listPost.set(0,session.get(Event.class,aLong));
        }


        return new EventBuilderImpl(listPost.get(0).getId(), listPost.get(0).getUser(),listPost.get(0).getDate(),listPost.get(0).getFile()).build();
    }

    @Override
    public List<Event> getAll() {
        List<Event> listPost;
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
          session.beginTransaction();
            listPost= session.createQuery("FROM Event ").list();



        }
        return listPost;

    }

    @Override
    public Event save(Event account) {
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        }



        return account;
    }

    @Override
    public Event update(Event file) {
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
