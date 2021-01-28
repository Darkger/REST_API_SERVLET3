package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.Event;
import com.eugene.crude.crude.practic.repository.EventRepository;
import com.eugene.crude.crude.practic.repository.hibernate.EventRepositoryImpl;
import java.sql.SQLException;
import java.util.List;


public class EventController {
    EventRepository postRepository;


    public EventController() {

        this.postRepository = new EventRepositoryImpl();
    }

    public Event save(Event post) {

        post = postRepository.save(post);
        if (post == null)
            return null;
        else
            return post;
    }

    public void deleteById(String str) {

        postRepository.deleteById(Integer.parseInt(str));
    }

    public Event getElementById(String str) {

        Event post = null;
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

    public Event update(Event post) {

        post = postRepository.update(post);
        if (post != null) {
            return post;
        } else return null;


    }

    public List<Event> getAll() {

        List<Event> postList = null;
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
