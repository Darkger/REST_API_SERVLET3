package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.PostOrRegion;

import java.io.IOException;
import java.util.List;

public interface PostController extends Controller <PostOrRegion>{

        public PostOrRegion save(PostOrRegion post) throws IOException;

        void deleteById(String str) throws IOException ;

        public PostOrRegion getElementById(String  str) throws IOException;


        public PostOrRegion Update(PostOrRegion post) throws IOException ;

        public List<PostOrRegion> getAll() throws IOException ;


}
