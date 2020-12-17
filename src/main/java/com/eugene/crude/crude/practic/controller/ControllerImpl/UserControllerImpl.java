package com.eugene.crude.crude.practic.controller.ControllerImpl;



import com.eugene.crude.crude.practic.model.User;

import com.eugene.crude.crude.practic.repository.jdbc.UserRepositoryImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class UserControllerImpl  {
    UserRepositoryImpl userRepository;
    Connection connection;

    public UserControllerImpl(Connection connection) {
        this.connection = connection;
        this.userRepository= new UserRepositoryImpl(connection);

    }

    public User save(User user) throws IOException {

        user = userRepository.save(user);
        if (user == null)
            return null;
        else
            return user;
    }

    public void deleteById(String str) throws IOException {

        userRepository.deleteById(Integer.parseInt(str));
    }


    public User getElementById(String str) throws IOException {

        User user = userRepository.getById(Integer.parseInt(str));
        if (user == null)
            return null;
        else {
            return user;
        }
    }

    public User update(User user) throws IOException {

        user = userRepository.update(user);
        if (user != null) {
            return user;
        } else return null;


    }

    public List<User> getAll() throws IOException {

        List<User> userList = userRepository.getAll();
        if (userList == null)
            return null;
        else
            return userList;
    }

}
