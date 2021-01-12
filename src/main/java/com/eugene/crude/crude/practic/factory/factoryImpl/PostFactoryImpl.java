package com.eugene.crude.crude.practic.factory.factoryImpl;

import com.eugene.crude.crude.practic.factory.PostFactory;
import com.eugene.crude.crude.practic.model.Post;

public class PostFactoryImpl implements PostFactory {
    @Override
    public Post create() {
        return  new Post();
    }
}
