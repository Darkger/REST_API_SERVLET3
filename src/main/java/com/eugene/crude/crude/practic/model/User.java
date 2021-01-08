package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.builder.builderImpl.UserBuilderImpl;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lasName;
    @OneToMany(cascade = CascadeType. ALL, fetch=FetchType.EAGER)
    @JoinTable(
            name = "blog",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts;

    @OneToOne(cascade=CascadeType.MERGE )
    @JoinColumn(name = "region_id")
    private Region region;

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public User() {
    }


}
