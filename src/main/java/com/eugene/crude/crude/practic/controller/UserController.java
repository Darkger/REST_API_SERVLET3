package com.eugene.crude.crude.practic.controller;

import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.repository.hibernate.UserRepositoryImpl;

import java.util.List;

public class UserController {
    UserRepositoryImpl userRepository;


    public UserController() {

        this.userRepository = new UserRepositoryImpl();

    }

    public User save(User user) {

        user = userRepository.save(user);
        if (user == null)
            return null;
        else
            return user;
    }

    public void deleteById(String str) {

        userRepository.deleteById(Integer.parseInt(str));
    }


    public User getElementById(String str) {

        User user = userRepository.getById(Integer.parseInt(str));
        if (user == null)
            return null;
        else {
            return user;
        }
    }

    public User update(User user) {

        user = userRepository.update(user);
        if (user != null) {
            return user;
        } else return null;


    }

    public List<User> getAll() {

        List<User> userList = userRepository.getAll();
        if (userList == null)
            return null;
        else
            return userList;
    }

}
