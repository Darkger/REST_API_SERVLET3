package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;

import javax.persistence.*;


@Entity
@Table(name = "region")
public class Region   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="region_id")
    private Integer id;
    @Column (name = "name")
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

    public String getCharRegName() {
        return charRegName;
    }

    public void setCharRegName(String charRegName) {
        this.charRegName = charRegName;
    }
}
