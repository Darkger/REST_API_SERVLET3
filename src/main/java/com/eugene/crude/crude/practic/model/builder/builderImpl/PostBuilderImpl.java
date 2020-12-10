package com.eugene.crude.crude.practic.model.builder.builderImpl;

import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.builder.Builder;

public class PostBuilderImpl implements Builder {

    private Integer id;
    private String content;

    public PostBuilderImpl setId(Integer id) {
        this.id = id;
        return this;
    }

    public PostBuilderImpl setContent(String content) {
        this.content = content;
        return this;
    }

    public PostBuilderImpl() {

    }
    public PostBuilderImpl(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    public Post build(){
        return  new Post(this);
    }
}
