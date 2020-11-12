package com.eugene.crude.crude.practic.controller.ControllerImpl;


import com.eugene.crude.crude.practic.controller.PostController;
import com.eugene.crude.crude.practic.model.Post;

import com.eugene.crude.crude.practic.repository.PostRepository;
import com.eugene.crude.crude.practic.repository.repositoryIO.PostRepositoryImpl;


import java.io.IOException;
import java.util.List;

public class PostControllerImpl  implements PostController {
    PostRepository postRepository = new PostRepositoryImpl();




    public Post save(Post post) throws IOException {

        post = postRepository.save(post);
        if (post == null)
            return null;
        else
            return post;
    }

    public void deleteById(String str) throws IOException {

        postRepository.deleteById(Long.parseLong(str));
    }

    public Post getElementById(String str) throws IOException {

        Post post = postRepository.getById(Long.parseLong(str));
        if (post == null)
            return null;
        else {
            return post;
        }
    }

    public Post update(Post post) throws IOException {

        post = postRepository.update(post);
        if (post != null) {
            return post;
        } else return null;


    }

    public List<Post> getAll() throws IOException {

        List<Post> postList = postRepository.getAll();
        if (postList==null)
            return null;
        else
            return postList;
    }

}
