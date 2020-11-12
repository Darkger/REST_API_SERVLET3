package com.eugene.crude.crude.practic.controller;


import com.eugene.crude.crude.practic.model.Region;

import java.io.IOException;
import java.util.List;

public interface RegionController extends Controller<Region>  {
    @Override
    Region save(Region region) throws IOException;

    @Override
    void deleteById(String str) throws IOException;

    @Override
   Region getElementById(String str) throws IOException;

    @Override
    Region update(Region region) throws IOException;

    @Override
    List<Region> getAll() throws IOException;
}
