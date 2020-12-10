package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.builder.builderImpl.PostBuilderImpl;

public class Post   {
    private Integer id;
    private String name;


    public Post(PostBuilderImpl postBuilder) {
        id=postBuilder.getId();
        name =postBuilder.getContent();

    }

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
