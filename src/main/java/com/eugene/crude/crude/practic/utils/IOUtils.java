package com.eugene.crude.crude.practic.utils;

import com.eugene.crude.crude.practic.model.Post;


import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 public class IOUtils {


    public static int getValidId(List<String> listReg, int id, Class clazz) {
        List<Integer> listPostId = new ArrayList<>();
        Post post;
        Region region;
        User user1;

        for (String str : listReg) {
            if (clazz.equals(Post.class)) {

                post = new Gson().fromJson(str, Post.class);
                listPostId.add(Integer.parseInt(post.getId()));
            }
            if (clazz.equals(Region.class)) {
                region = new Gson().fromJson(str, Region.class);
                listPostId.add(Integer.parseInt(region.getId()));
            }
            if (clazz.equals(User.class)) {
                  user1 = new Gson().fromJson(str, User.class);
                listPostId.add(Integer.parseInt(user1.getId()));
            }
        }
        if(listPostId.isEmpty()){
            id=1;
        }else
        if(listPostId.contains(id)|| id==0)
        {
            Optional<Integer> maxVal=listPostId.stream().max(Integer::compare);
            id=maxVal.get()+1;
        }
        return id;
    }


}
