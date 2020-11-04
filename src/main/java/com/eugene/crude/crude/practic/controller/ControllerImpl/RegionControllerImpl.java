package com.eugene.crude.crude.practic.controller.ControllerImpl;


import com.eugene.crude.crude.practic.controller.RegionController;
import com.eugene.crude.crude.practic.model.*;
import com.eugene.crude.crude.practic.repository.RegionRepository;
import com.eugene.crude.crude.practic.repository.repositoryIO.RegionRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class RegionControllerImpl implements RegionController {
    RegionRepository regionPostRepository = new RegionRepositoryImpl();


    public PostOrRegion save(PostOrRegion post) throws IOException {

        PostOrRegion region1= regionPostRepository.save(post);
        if (post == null)
            return null;
        else
            return region1;
    }

    public void deleteById(String str) throws IOException {

        regionPostRepository.deleteById(Long.parseLong(str));
    }

    public PostOrRegion getElementById(String str) throws IOException {

        PostOrRegion post = regionPostRepository.getById(Long.parseLong(str));
        if (post == null)
            return null;
        else {
            return post;
        }
    }

    public PostOrRegion Update(PostOrRegion post) throws IOException {

        post = regionPostRepository.update(post);
        if (post != null) {
            return post;
        } else return null;


    }

    public List<PostOrRegion> getAll() throws IOException {

        List<PostOrRegion> postList = regionPostRepository.getAll();
        if (postList==null)
            return null;
        else
            return postList;
    }

}
