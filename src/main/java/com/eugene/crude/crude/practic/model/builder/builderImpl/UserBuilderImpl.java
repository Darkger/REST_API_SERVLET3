package com.eugene.crude.crude.practic.model.builder.builderImpl;

import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.model.builder.Builder;

import java.util.List;

public class UserBuilderImpl implements Builder {
    int id;
    String firstName;
    String lasName;
    List<File> posts;
    Region region;

    public UserBuilderImpl() {
    }

    public int getId() {
        return id;
    }

    public UserBuilderImpl setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserBuilderImpl setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLasName() {
        return lasName;
    }

    public UserBuilderImpl setLasName(String lasName) {
        this.lasName = lasName;
        return this;
    }

    public List<File> getPosts() {
        return posts;
    }

    public UserBuilderImpl setPosts(List<File> posts) {
        this.posts = posts;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public UserBuilderImpl setRegion(Region region) {
        this.region = region;
        return this;
    }

    public UserBuilderImpl(int id, String firstName, String lasName, List<File> posts, Region region) {
        this.id = id;
        this.firstName = firstName;
        this.lasName = lasName;
        this.posts = posts;
        this.region = region;
    }

    @Override
    public User build() {
        return  new User(this);
    }
}
