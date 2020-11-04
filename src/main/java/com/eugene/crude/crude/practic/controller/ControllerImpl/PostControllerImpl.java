package com.eugene.crude.crude.practic.controller.ControllerImpl;


import com.eugene.crude.crude.practic.controller.PostController;
import com.eugene.crude.crude.practic.model.Post;

import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.repository.PostRepository;
import com.eugene.crude.crude.practic.repository.repositoryIO.PostRepositoryImpl;


import java.io.IOException;
import java.util.List;

public class PostControllerImpl  implements PostController {
    PostRepository postRepository = new PostRepositoryImpl();




    public PostOrRegion save(PostOrRegion post) throws IOException {

        post = postRepository.save(post);
        if (post == null)
            return null;
        else
            return postRepository.save(post);
    }

    public void deleteById(String str) throws IOException {

        postRepository.deleteById(Long.parseLong(str));
    }

    public PostOrRegion getElementById(String str) throws IOException {

        PostOrRegion post = postRepository.getById(Long.parseLong(str));
        if (post == null)
            return null;
        else {
            return post;
        }
    }

    public PostOrRegion Update(PostOrRegion post) throws IOException {

        post = postRepository.update(post);
        if (post != null) {
            return post;
        } else return null;


    }

    public List<PostOrRegion> getAll() throws IOException {

        List<PostOrRegion> postList = postRepository.getAll();
        if (postList==null)
            return null;
        else
            return postList;
    }

}
