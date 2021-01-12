package com.eugene.crude.crude.practic.factory.factoryImpl;

import com.eugene.crude.crude.practic.factory.UserFactory;
import com.eugene.crude.crude.practic.model.User;

public class UserFactoryImpl implements UserFactory {
    @Override
    public User create() {
        return new User();
    }
}
