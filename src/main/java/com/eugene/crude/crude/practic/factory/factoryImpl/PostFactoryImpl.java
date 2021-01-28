package com.eugene.crude.crude.practic.factory.factoryImpl;

import com.eugene.crude.crude.practic.factory.FileFactory;
import com.eugene.crude.crude.practic.model.File;

public class PostFactoryImpl implements FileFactory {
    @Override
    public File create() {
        return  new File();
    }
}
