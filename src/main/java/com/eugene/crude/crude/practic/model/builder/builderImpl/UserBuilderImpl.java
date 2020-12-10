package com.eugene.crude.crude.practic.model.builder.builderImpl;

import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.model.builder.Builder;

import java.util.List;

public class UserBuilderImpl implements Builder {
    Integer id;
    String firstName;
    String lasName;
    List<Post> posts;
    Region region;

    public UserBuilderImpl() {
    }

    public Integer getId() {
        return id;
    }

    public UserBuilderImpl setId(Integer id) {
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

    public List<Post> getPosts() {
        return posts;
    }

    public UserBuilderImpl setPosts(List<Post> posts) {
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

    public UserBuilderImpl(Integer id, String firstName, String lasName, List<Post> posts, Region region) {
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
