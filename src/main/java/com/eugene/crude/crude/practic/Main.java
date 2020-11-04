package com.eugene.crude.crude.practic;




import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.repository.repositoryIO.RegionRepositoryImpl;
import com.eugene.crude.crude.practic.view.ViewHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main  {
    public static void main(String[] args) throws IOException {
       // Path file = Paths.get("C:\\javaFiles\\posts.json");
        ViewHelper viewHelper = new ViewHelper();
        viewHelper.mainHelper();
      //  RegionRepositoryImpl postRepository = new RegionRepositoryImpl();
      // postRepository.save(new Post("1","abrakadabra"));
     // postRepository.save(new Post("10","abrakadabrea"));
     // postRepository.deleteById((long)10);
        // postRepository.update(new Post("1","k,oko"));
     //   Gson gson = new Gson();
   //     List<Post> jk=postRepository.getAll();
     //   jk.stream().forEach(s->System.out.println(s.getId()+", "+s.getContent()));
       // String str =gson.toJson(new Post("1","sdfsdf"));
      //  Files.writeString(file,str);
      // List<String> list = Files.readAllLines(file);
      // Post post = gson.fromJson(list.get(0),Post.class);
     //  System.out.println(post.getId()+", "+post.getContent());
    }
}
