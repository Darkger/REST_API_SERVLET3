package com.eugene.crude.crude.practic.repository;


import com.eugene.crude.crude.practic.model.User;

import java.util.List;

public interface UserRepository extends Genericrepository<User, Integer> {
    @Override
    User getById(Integer aLong);

    @Override
    List<User> getAll();

    @Override
    User save(User user);

    @Override
    User update(User user);

    @Override
    void deleteById(Integer aLong);
}
