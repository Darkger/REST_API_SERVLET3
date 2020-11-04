package com.eugene.crude.crude.practic.repository.repositoryIO;

import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.repository.IOUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public  class IOUtilsRegionImpl implements IOUtils {

 @Override
public  int getValidId(List<String> listReg,int id){
     List<Integer> listPostId= new ArrayList<>();

     for(String str:listReg) {
         Region region = new Gson().fromJson(str,Region.class);
          listPostId.add(Integer.parseInt(region.getId()));
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
