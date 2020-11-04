package com.eugene.crude.crude.practic.model;

public class Region  implements  PostOrRegion {
    private String id;
    private String charRegName;






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return charRegName;
    }

    public void setContent(String charRegName) {
        this.charRegName = charRegName;
    }
}
