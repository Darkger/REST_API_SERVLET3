package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.*;
import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;
import com.eugene.crude.crude.practic.repository.RegionRepository;
import com.eugene.crude.crude.practic.repository.hibernate.RegionRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class RegionController {

    RegionRepository regionRepository;

    public RegionController() {
        this.regionRepository = new RegionRepositoryImpl();
    }


    public Region save(Region region) {
      RegionBuilderImpl region1 = new RegionBuilderImpl();
      region1.setId(region.getId()).setName(region.getCharRegName());
      region = regionRepository.findByName(region);
        if (region==null){
            return regionRepository.save(region1.build());
        }
         return region;

    }

    public void deleteById(String str) {

        regionRepository.deleteById(Integer.parseInt(str));
    }

    public Region getElementById(String str) {

        Region region = null;
        try {
            region = regionRepository.getById(Integer.parseInt(str));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (region == null)
            return null;
        else {
            return region;
        }
    }


    public Region update(Region region) {

        region = regionRepository.update(region);
        if (region != null) {
            return region;
        } else return null;


    }

    public List<Region> getAll() {

        List<Region> postList = null;
        try {
            postList = regionRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (postList == null)
            return null;
        else
            return postList;
    }

}
