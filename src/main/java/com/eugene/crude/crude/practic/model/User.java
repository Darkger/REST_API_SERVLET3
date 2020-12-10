package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.builder.builderImpl.UserBuilderImpl;

import java.util.List;

public class User {
    Integer id;
    String firstName;
    String lasName;
    List<Post> posts;
    Region region;

    public User(UserBuilderImpl userBuilder) {
        this.id = userBuilder.getId();
        this.firstName = userBuilder.getFirstName();
        this.lasName = userBuilder.getLasName();
        this.posts = userBuilder.getPosts();
        this.region = userBuilder.getRegion();
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public  Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public User() {
    }


}
