package com.eugene.crude.crude.practic.model;

import java.util.Date;

public class Post  implements PostOrRegion {
    private String id;
    private String content;
    private Date create ;
    private Date update ;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
