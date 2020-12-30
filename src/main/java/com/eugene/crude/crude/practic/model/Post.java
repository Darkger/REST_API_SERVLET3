package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.builder.builderImpl.PostBuilderImpl;

import javax.persistence.*;


@Entity
@Table(name = "post")
public class Post   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="post_id")

    private Integer id;
    @Column(name ="name")
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
