package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;

public class Region   {
    private Integer id;
    private String charRegName;

    public Region(RegionBuilderImpl regionBuilder) {
        this.id = regionBuilder.getId();
        this.charRegName = regionBuilder.getName();
    }

    public Region() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getContent() {
        return charRegName;
    }

    public void setContent(String charRegName) {
        this.charRegName = charRegName;
    }
}
