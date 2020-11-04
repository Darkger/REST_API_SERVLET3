package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.User;

import java.io.IOException;
import java.util.List;

public interface UserController extends Controller<User> {
    @Override
    User save(User post) throws IOException;

    @Override
    void deleteById(String str) throws IOException;

    @Override
    User getElementById(String str) throws IOException;

    @Override
    User Update(User post) throws IOException;

    @Override
    List<User> getAll() throws IOException;
}
