package com.eugene.crude.crude.practic.model.builder.builderImpl;

import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.builder.Builder;

public class RegionBuilderImpl implements Builder {
   Integer id;
   String name;

    public Integer getId() {
        return id;
    }

    public RegionBuilderImpl setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegionBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    public RegionBuilderImpl() {
    }

    public RegionBuilderImpl(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Region build() {
        return new Region(this);
    }
}
