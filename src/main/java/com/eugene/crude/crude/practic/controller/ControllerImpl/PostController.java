package com.eugene.crude.crude.practic.controller.ControllerImpl;

import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.repository.PostRepository;
import com.eugene.crude.crude.practic.repository.hibernate.PostRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class PostController {
    PostRepository postRepository;


    public PostController() {

        this.postRepository = new PostRepositoryImpl();
    }

    public Post save(Post post) {

        post = postRepository.save(post);
        if (post == null)
            return null;
        else
            return post;
    }

    public void deleteById(String str) {

        postRepository.deleteById(Integer.parseInt(str));
    }

    public Post getElementById(String str) {

        Post post = null;
        try {
            post = postRepository.getById(Integer.parseInt(str));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (post == null)
            return null;
        else {
            return post;
        }
    }

    public Post update(Post post) {

        post = postRepository.update(post);
        if (post != null) {
            return post;
        } else return null;


    }

    public List<Post> getAll() {

        List<Post> postList = null;
        try {
            postList = postRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (postList == null)
            return null;
        else
            return postList;
    }

}
