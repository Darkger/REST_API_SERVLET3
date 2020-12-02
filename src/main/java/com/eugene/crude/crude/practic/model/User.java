package com.eugene.crude.crude.practic.model;

import java.util.List;

public class User {
    String id;
    String firstName;

    public User(String id, String firstName, String lasName, List<Integer> posts, int region) {
        this.id = id;
        this.firstName = firstName;
        this.lasName = lasName;
        this.posts = posts;
        this.region = region;
    }

    String lasName;
    List<Integer> posts;
    int region;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    public List<Integer> getPosts() {
        return posts;
    }

    public void setPosts(List<Integer> posts) {
        this.posts = posts;
    }

    public  Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public User() {
    }

    public User(String id, String firstName, String lastName, List<Integer> posts, Integer region) {
        this.id = id;
        this.firstName = firstName;
        this.lasName= lastName;
        this.posts = posts;
        this.region = region;
    }
}
