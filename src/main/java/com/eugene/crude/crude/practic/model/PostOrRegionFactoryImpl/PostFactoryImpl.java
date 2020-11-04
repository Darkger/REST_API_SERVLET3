package com.eugene.crude.crude.practic.model.PostOrRegionFactoryImpl;

import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.model.PostOrRegionFactory;

public class PostFactoryImpl implements PostOrRegionFactory {
    @Override
    public PostOrRegion create() {
        return  new Post();
    }
}
