package com.eugene.crude.crude.practic.model;

import com.eugene.crude.crude.practic.model.PostOrRegionFactoryImpl.PostFactoryImpl;
import com.eugene.crude.crude.practic.model.PostOrRegionFactoryImpl.RegionFactoryImpl;

public class PostOrRegionImpl {
    PostOrRegionFactory postOrRegionFactory;

    public PostOrRegion createPostOrRegion(String str) {
        if (str.equals("region")) {
            postOrRegionFactory = new RegionFactoryImpl();
            return postOrRegionFactory.create();
        }
        if (str.equals("post")) {
            postOrRegionFactory = new PostFactoryImpl();
            return postOrRegionFactory.create();
        } else {
            return null;
        }
    }
}
