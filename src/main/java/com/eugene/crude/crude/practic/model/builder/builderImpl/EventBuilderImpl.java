package com.eugene.crude.crude.practic.model.builder.builderImpl;

import com.eugene.crude.crude.practic.model.*;
import com.eugene.crude.crude.practic.model.builder.Builder;


import java.util.Date;

public class EventBuilderImpl implements Builder {
    private Long id;
    private User user;
    private Date date;
    private File file;

    public EventBuilderImpl(Long id, User user, Date date, File file) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public EventBuilderImpl setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public EventBuilderImpl setUser(User user) {
        this.user = user;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public EventBuilderImpl setDate(Date date) {
        this.date = date;
        return this;
    }

    public File getFile() {
        return file;
    }

    public EventBuilderImpl setFile(File file) {
        this.file = file;
        return this;
    }

    public Event build(){
        return  new Event(this);
    }
}
