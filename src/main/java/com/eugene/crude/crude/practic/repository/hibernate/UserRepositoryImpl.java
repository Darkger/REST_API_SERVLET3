package com.eugene.crude.crude.practic.repository.hibernate;

import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.model.builder.builderImpl.UserBuilderImpl;
import com.eugene.crude.crude.practic.repository.UserRepository;
import com.eugene.crude.crude.practic.utils.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {



    public UserRepositoryImpl() {

    }

    @Override
    public User getById(Integer aLong)  {
        List<User> listUser;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
             session.beginTransaction();
            Query query = session.createQuery(" FROM User  WHERE id=:param1");
            query.setParameter("param1", aLong);
            listUser = query.list();

        }
        return new UserBuilderImpl(listUser.get(0).getId(), listUser.get(0).getFirstName(), listUser.get(0).getLasName(), listUser.get(0).getPosts(), listUser.get(0).getRegion()).build();


    }

    @Override
    public List<User> getAll() {
        List<User> listUser;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
           session.beginTransaction();
            Query query = session.createQuery("FROM User ");

            listUser = query.list();


        }

        return listUser;


    }

    @Override
    public User save(User user) {

        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();

        }

        return user;
    }

    @Override
    public User update(User user) {
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();

        }
        return user;
    }

    @Override
    public void deleteById(Integer aLong) {

        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();


            session.delete(session.get(User.class,aLong));

            transaction.commit();

        }
    }
}
