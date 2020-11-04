package com.eugene.crude.crude.practic.repository.repositoryIO;

import com.eugene.crude.crude.practic.model.Post;

import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.repository.IOUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public  class IOUtilsPostImpl implements IOUtils {

 @Override
public  int getValidId(List<String> listReg,int id){
     List<Integer> listPostId= new ArrayList<>();

     for(String str:listReg) {
         PostOrRegion user = new Gson().fromJson(str,Post.class);
          listPostId.add(Integer.parseInt(user.getId()));
     }
     if(listPostId.contains(id))
     {
         Optional<Integer> maxVal=listPostId.stream().max(Integer::compare);
         id=maxVal.get()+1;
     }
    return id;
}

}
