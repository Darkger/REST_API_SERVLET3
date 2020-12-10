package com.eugene.crude.crude.practic.controller.ControllerImpl;


import com.eugene.crude.crude.practic.controller.UserController;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.repository.RegionRepository;
import com.eugene.crude.crude.practic.repository.bdRepositoty.RegionRepositoryImpl;
import com.eugene.crude.crude.practic.repository.bdRepositoty.UserRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class UserControllerImpl  implements UserController {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
RegionRepository regionRepository = new RegionRepositoryImpl();
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
        if (userList==null)
            return null;
        else
            return userList;
    }

}
