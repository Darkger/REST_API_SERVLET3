package com.eugene.crude.crude.practic.repository.repositoryIO;




import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.repository.IOUtils;
import com.eugene.crude.crude.practic.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Gson gson = new GsonBuilder().registerTypeAdapter(PostOrRegion.class, new InterfaceAdapter<PostOrRegion>())
            .create();


    private Path userFile = Paths.get("C:\\javaFiles\\user.json");
    private RegionRepositoryImpl postRepository = new RegionRepositoryImpl();

    @Override

    public User getById(Long id) {
        try {
            List<String> listUser = Files.readAllLines(userFile);
            if (!listUser.isEmpty()) {
                for (String str : listUser) {
                    if (str.contains("\"id\":\"" + id + "\"")) {
                        User user = gson.fromJson(str, User.class);
                        return user;
                    }
                }
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        try {
            List<String> listUser = Files.readAllLines(userFile);
            List<User> listUserObj = new ArrayList<>();

            if (!listUser.isEmpty()) {
                for (String str : listUser) {
                    User user = gson.fromJson(str, User.class);
                    listUserObj.add(user);
                }
                return listUserObj;
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public User save(User user) {
        try {
            if (!Files.exists(userFile)) {
                Files.createFile(userFile);
            }
            List<String> listReg = Files.readAllLines(userFile);
            IOUtils ioUtils = new IOUtilsUserImpl();
            int validId = ioUtils.getValidId(listReg, Integer.parseInt(user.getId()));
            user.setId(String.valueOf(validId));
            try {

                String strToWrite = gson.toJson(user);
                Files.writeString(userFile, strToWrite + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("ошибка записи в файл3");
            }
            return user;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public User update(User user) {
        try {
            List<String> listUser = Files.readAllLines(userFile);
            User userNew = new User();
            String fdf = "\"id\":\"" + user.getId() + "\"";
            if (!listUser.isEmpty()) {
                for (int i = 0; i < listUser.size(); i++) {

                    if (listUser.get(i).contains(fdf)) {
                        userNew = gson.fromJson(listUser.get(i), User.class);
                        userNew.setFirstName(user.getFirstName());
                        userNew.setLasName(user.getLasName());
                        userNew.setPosts(user.getPosts());
                        userNew.setRegion(user.getRegion());
                        listUser.set(i, gson.toJson(userNew));
                        break;
                    }
                }
                Files.delete(userFile);
                Files.createFile(userFile);
                for (String str : listUser) {
                    Files.writeString(userFile, str + "\n", StandardOpenOption.APPEND);
                }
            }
            return userNew;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            List<String> listUser = Files.readAllLines(userFile);

            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).contains(("\"id\":\"" + id + "\""))) {
                    listUser.set(i, "DELETE");
                }
            }
            Files.delete(userFile);
            Files.createFile(userFile);
            listUser.stream().forEach(str -> {
                if (!str.equals("DELETE")) {
                    try {
                        Files.writeString(userFile, str + "\n", StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
