package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.model.Region;

import java.io.IOException;
import java.util.List;

public interface RegionController extends Controller<PostOrRegion>  {
    @Override
    PostOrRegion save(PostOrRegion post) throws IOException;

    @Override
    void deleteById(String str) throws IOException;

    @Override
    PostOrRegion getElementById(String str) throws IOException;

    @Override
    PostOrRegion Update(PostOrRegion post) throws IOException;

    @Override
    List<PostOrRegion> getAll() throws IOException;
}
