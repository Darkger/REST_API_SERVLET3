package com.eugene.crude.crude.practic.controller.ControllerImpl;


import com.eugene.crude.crude.practic.model.Post;

import com.eugene.crude.crude.practic.repository.PostRepository;
import com.eugene.crude.crude.practic.repository.jdbc.PostRepositoryImpl;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class PostControllerImpl  {
    PostRepository postRepository;
    Connection connection;

    public PostControllerImpl(Connection connection) {
        this.connection = connection;
        this.postRepository = new PostRepositoryImpl(connection);
    }

    public Post save(Post post) throws IOException {

        post = postRepository.save(post);
        if (post == null)
            return null;
        else
            return post;
    }

    public void deleteById(String str) throws IOException {

        postRepository.deleteById(Integer.parseInt(str));
    }

    public Post getElementById(String str) throws IOException {

        Post post = postRepository.getById(Integer.parseInt(str));
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
        if (postList == null)
            return null;
        else
            return postList;
    }

}
