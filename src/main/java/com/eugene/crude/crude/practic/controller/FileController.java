package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.repository.FileRepository;
import com.eugene.crude.crude.practic.repository.hibernate.FileRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class FileController {
    FileRepository postRepository;


    public FileController() {

        this.postRepository = new FileRepositoryImpl();
    }

    public File save(File post) {

        post = postRepository.save(post);
        if (post == null)
            return null;
        else
            return post;
    }

    public void deleteById(String str) {

        postRepository.deleteById(Integer.parseInt(str));
    }

    public File getElementById(String str) {

        File post = null;
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

    public File update(File post) {

        post = postRepository.update(post);
        if (post != null) {
            return post;
        } else return null;


    }

    public List<File> getAll() {

        List<File> postList = null;
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
