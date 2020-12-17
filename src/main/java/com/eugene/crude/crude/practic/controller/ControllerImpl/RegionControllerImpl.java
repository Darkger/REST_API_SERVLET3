package com.eugene.crude.crude.practic.controller.ControllerImpl;



import com.eugene.crude.crude.practic.model.*;
import com.eugene.crude.crude.practic.repository.RegionRepository;
import com.eugene.crude.crude.practic.repository.jdbc.RegionRepositoryImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class RegionControllerImpl  {
    Connection connection;
    RegionRepository regionPostRepository;
    public RegionControllerImpl(Connection connection) {
        this.connection = connection;
        this.regionPostRepository = new RegionRepositoryImpl(connection);
    }




    public Region save(Region region) throws IOException {

       Region region1= regionPostRepository.save(region);

            return region1;
    }

    public void deleteById(String str) throws IOException {

        regionPostRepository.deleteById(Integer.parseInt(str));
    }

    public Region getElementById(String str) throws IOException {

       Region region = regionPostRepository.getById(Integer.parseInt(str));
        if (region == null)
            return null;
        else {
            return region;
        }
    }



    public Region update(Region region) throws IOException {

        region = regionPostRepository.update(region);
        if (region != null) {
            return region;
        } else return null;


    }

    public List<Region> getAll() throws IOException {

        List<Region> postList = regionPostRepository.getAll();
        if (postList==null)
            return null;
        else
            return postList;
    }

}
