package com.eugene.crude.crude.practic.model;


import com.eugene.crude.crude.practic.model.builder.builderImpl.EventBuilderImpl;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date date;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "file_id")
    private File file;

    public Event(EventBuilderImpl eventBuilder) {
        this.user = eventBuilder.getUser();
        this.date = eventBuilder.getDate();
        this.file = eventBuilder.getFile();
    }

    public Event() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
