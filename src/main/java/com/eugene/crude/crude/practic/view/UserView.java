package com.eugene.crude.crude.practic.view;

import com.eugene.crude.crude.practic.controller.ControllerImpl.PostController;
import com.eugene.crude.crude.practic.controller.ControllerImpl.RegionController;
import com.eugene.crude.crude.practic.controller.ControllerImpl.UserController;
import com.eugene.crude.crude.practic.model.*;
import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;
import com.eugene.crude.crude.practic.model.builder.builderImpl.UserBuilderImpl;
import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;


import java.util.ArrayList;
import java.util.List;

public class UserView implements View {

    UserController userController;
    RegionController regionController;
    PostController postController;


    public UserView()  {

        this.userController = new UserController();
        this.postController = new PostController();
        this.regionController = new RegionController();
    }

    public void viewDeleteById(String str)  {

        userController.deleteById(str);
        System.out.println("Пользователь с id=" + str + " удален из файла");
    }

    public void viewSave(User user)  {

        user = userController.save(user);
        if (user != null)
            System.out.println("Пользователь " + "'" + user.getFirstName() + " " + user.getLasName() + " '" + " сохранен с id=" + user.getId());
        else System.out.println("Ошбика:Пользователь не может быть сохранен!");
    }

    public void viewGetAll()  {

        List<User> userList = userController.getAll();
        if (userList.isEmpty())
            System.out.println("Файл пуст");
        else {
            System.out.println("Список регионов:");
            if (userList != null) {
                for (User user : userList) {
                    String str = "";
                    List<Post> gg = user.getPosts();
                    for (Post p : gg) {

                        str += p.getId() + ",";
                    }
                    str = str.substring(0, str.length() - 1);
                    System.out.println(user.getId() + "," + user.getFirstName() + "," + user.getLasName() + "," + "[" + str + "]" + "," + user.getRegion().getId());
                }
            } else System.out.println("Список пуст");
        }
    }

    public void viewUpdate(User user)  {

        User user1 = userController.update(user);
        if (user1 != null)
            System.out.println("Идентификатор id=" + user.getId() + " теперь присвоен пользователю '" + user.getFirstName() + " " + user.getLasName() + "'");
        else System.out.println("Ошибка:Пользователь не может быть изменен");
    }

    public void viewGetUserById(String str) {

        User user = userController.getElementById(str);
        if (user != null)
            System.out.println("Идентификатор id=" + user.getId() + "принадлежит пользователю'" + user.getFirstName() + " " + user.getLasName() + "'");
        else System.out.println("Регион не найден!");
    }

    public String routing()  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("РАБОТА С ФАЙЛОМ  'user.txt':\n\n");
        System.out.println("Доступный список действий:\n ");
        System.out.println("1. Введите команду -'LIST'   чтобы получить список пользователей");
        System.out.println("2. Введите команду -'BYID'   чтобы получить пользователя по id ");
        System.out.println("3. Введите команду -'SAVE'   чтобы сохранить пользователя");
        System.out.println("4. Введите команду -'UPDATE' чтобы изменить пользователя");
        System.out.println("5. Введите команду -'DELETE' чтобы удалить  пользователя");
        System.out.println("6. Введите команду -'POST' для перехода к файлу 'post.json':");
        System.out.println("7. Введите команду -'REG' для перехода к файлу 'region.json':");
        String str = null;
        try {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (str) {
            case "LIST": {
                viewGetAll();
                break;
            }
            case "BYID": {
                System.out.println("Введите id: ");
                String id = null;
                try {
                    id = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewGetUserById(id);
                break;

            }
            case "SAVE": {
                System.out.println("Введите id: ");
                String id = null;
                try {
                    id = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите имя пользователя: ");
                String userFirstName = null;
                try {
                    userFirstName = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите фамилию пользователя: ");
                String userLastName = null;
                try {
                    userLastName = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите id постов пользователя через запятую: ");
                String userPostId = null;
                try {
                    userPostId = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите  регион пользователя: ");
                String userRegion = null;
                try {
                    userRegion = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<Post> postList = new ArrayList<>();
                String postArray[] = userPostId.split(",");
                for (String str1 : postArray) {
                    postList.add(postController.getElementById(str1));
                }
                Region region = new RegionBuilderImpl().setName(userRegion).build();
                region = regionController.save(region);
                User user = new UserBuilderImpl(Integer.parseInt(id), userFirstName, userLastName, postList, region).build();
                viewSave(user);
                break;
            }
            case "UPDATE": {
                System.out.println("Введите id: ");
                String id = null;
                try {
                    id = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите имя пользователя: ");
                String userFirstName = null;
                try {
                    userFirstName = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите фамилию пользователя: ");
                String userLastName = null;
                try {
                    userLastName = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите id постов пользователя через запятую: ");
                String userPostId = null;
                try {
                    userPostId = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите  регион пользователя: ");
                String userRegion = null;
                try {
                    userRegion = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<Post> postList = new ArrayList<>();
                String postArray[] = userPostId.split(",");
                for (String str1 : postArray) {
                    postList.add(postController.getElementById(str1));
                }
                Region region = new RegionBuilderImpl().setName(userRegion).build();
                region = regionController.save(region);
                User user = new UserBuilderImpl(Integer.parseInt(id), userFirstName, userLastName, postList, region).build();
                viewUpdate(user);
                break;
            }
            case "DELETE": {
                System.out.println("Введите id1: ");
                String id = null;
                try {
                    id = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewDeleteById(id);
                break;
            }
            case "POST": {
                return "POST";
            }
            case "REG": {
                return "REG";
            }
        }
        System.out.println("Продолжить работу? Y/N :");
        try {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
