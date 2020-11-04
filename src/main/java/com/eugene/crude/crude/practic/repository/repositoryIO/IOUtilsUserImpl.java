package com.eugene.crude.crude.practic.repository.repositoryIO;


import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.repository.IOUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public  class IOUtilsUserImpl implements IOUtils {
    private static int maxValue = 1;
    private static List<String> listId = new ArrayList<>();
 @Override
public  int getValidId(List<String> listReg,int id){
     List<Integer> listPostId= new ArrayList<>();

     for(String str:listReg) {
          User post = new Gson().fromJson(str,User.class);
          listPostId.add(Integer.parseInt(post.getId()));
     }
     if(listPostId.contains(id))
     {
         Optional<Integer> maxVal=listPostId.stream().max(Integer::compare);
         id=maxVal.get()+1;
     }
    return id;
}

}
