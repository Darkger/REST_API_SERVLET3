package com.eugene.crude.crude.practic.controller.ControllerImpl;

import com.eugene.crude.crude.practic.model.*;
import com.eugene.crude.crude.practic.repository.RegionRepository;
import com.eugene.crude.crude.practic.repository.hibernate.RegionRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class RegionControllerImpl {

    RegionRepository regionPostRepository;

    public RegionControllerImpl() {
        this.regionPostRepository = new RegionRepositoryImpl();
    }


    public Region save(Region region) {

        Region region1 = regionPostRepository.save(region);

        return region1;
    }

    public void deleteById(String str) {

        regionPostRepository.deleteById(Integer.parseInt(str));
    }

    public Region getElementById(String str) {

        Region region = null;
        try {
            region = regionPostRepository.getById(Integer.parseInt(str));
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

        region = regionPostRepository.update(region);
        if (region != null) {
            return region;
        } else return null;


    }

    public List<Region> getAll() {

        List<Region> postList = null;
        try {
            postList = regionPostRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (postList == null)
            return null;
        else
            return postList;
    }

}
