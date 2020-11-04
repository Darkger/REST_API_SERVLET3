package com.eugene.crude.crude.practic.model;

import java.util.List;

public class User {
    String id;
    String firstName;
    String lasName;
    List<PostOrRegion> posts;
    PostOrRegion region;


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

    public List<PostOrRegion> getPosts() {
        return posts;
    }

    public void setPosts(List<PostOrRegion> posts) {
        this.posts = posts;
    }

    public  PostOrRegion getRegion() {
        return region;
    }

    public void setRegion(PostOrRegion region) {
        this.region = region;
    }

    public User() {
    }

    public User(String id, String firstName, String lastName, List<PostOrRegion> posts, PostOrRegion region) {
        this.id = id;
        this.firstName = firstName;
        this.lasName= lastName;
        this.posts = posts;
        this.region = region;
    }
}
