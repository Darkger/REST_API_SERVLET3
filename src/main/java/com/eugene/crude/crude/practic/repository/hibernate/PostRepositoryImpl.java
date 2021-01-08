package com.eugene.crude.crude.practic.repository.hibernate;


import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.builder.builderImpl.PostBuilderImpl;
import com.eugene.crude.crude.practic.repository.PostRepository;
import com.eugene.crude.crude.practic.utils.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {




    public PostRepositoryImpl( ) {

    }

    @Override
    public Post getById(Integer aLong)  {
        List<Post> listPost = new ArrayList<>();
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            session.beginTransaction();
          listPost.set(0,session.get(Post.class,aLong));
        }


        return new PostBuilderImpl(listPost.get(0).getId(), listPost.get(0).getName()).build();
    }

    @Override
    public List<Post> getAll() {
        List<Post> listPost;
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
          session.beginTransaction();
            listPost= session.createQuery("FROM Post").list();



        }
        return listPost;

    }

    @Override
    public Post save(Post post) {
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
        }



        return post;
    }

    @Override
    public Post update(Post post) {
        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();
            session.update(post);
            transaction.commit();
            session.close();
        }
        return post;
    }

    @Override
    public void deleteById(Integer aLong) {

        try(Session session = HibernateConnection.getSessionFactory().openSession()){
            Transaction transaction = null;
            transaction = session.beginTransaction();

            session.delete(session.get(Post.class,aLong));
            transaction.commit();
        }
        }
    }
