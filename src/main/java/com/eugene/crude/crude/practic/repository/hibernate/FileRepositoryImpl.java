package com.eugene.crude.crude.practic.repository.hibernate;


import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.model.builder.builderImpl.FileBuilderImpl;
import com.eugene.crude.crude.practic.repository.FileRepository;
import com.eugene.crude.crude.practic.utils.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {




    public FileRepositoryImpl( ) {

    }

    @Override
    public File getById(Integer aLong)  {
        List<File> listPost = new ArrayList<>();
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            session.beginTransaction();
          listPost.set(0,session.get(File.class,aLong));
        }


        return new FileBuilderImpl(listPost.get(0).getId(), listPost.get(0).getName(),listPost.get(0).getFileStatus(),listPost.get(0).getFileByte()).build();
    }

    @Override
    public List<File> getAll() {
        List<File> listPost;
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
          session.beginTransaction();
            listPost= session.createQuery("FROM File").list();



        }
        return listPost;

    }

    @Override
    public File save(File file) {
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.save(file);
            transaction.commit();
        }



        return file;
    }

    @Override
    public File update(File file) {
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
