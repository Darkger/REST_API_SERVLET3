package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.Post;


import java.io.IOException;
import java.util.List;

public interface PostController extends Controller <Post>{

        public Post save(Post post) throws IOException;

        void deleteById(String str) throws IOException ;

        public Post getElementById(String  str) throws IOException;


        public Post update(Post post) throws IOException ;

        public List<Post> getAll() throws IOException ;


}
