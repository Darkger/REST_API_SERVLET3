package com.eugene.crude.crude.practic.view;


import com.eugene.crude.crude.practic.controller.Controller;

import com.eugene.crude.crude.practic.controller.ControllerImpl.PostControllerImpl;

import com.eugene.crude.crude.practic.controller.PostController;
import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.model.PostOrRegionFactory;
import com.eugene.crude.crude.practic.model.PostOrRegionFactoryImpl.PostFactoryImpl;
import com.eugene.crude.crude.practic.model.PostOrRegionImpl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class PostView implements View {
    PostOrRegionImpl postOrRegion = new PostOrRegionImpl();
    PostController postController =  new PostControllerImpl();
    public void viewDeleteById(String str) throws IOException {

        postController.deleteById(str);
        System.out.println("Пост с id=" + str + " удален из файла");
    }

    public void viewSave(PostOrRegion post) throws IOException {

        post = postController.save(post);
        if (post != null)
            System.out.println("Пост" + "'" + post.getContent() + "'" + " сохранен с id=" + post.getId());
        else System.out.println("Ошбика:Пост не может быть сохранен!");
    }

    public void viewGetAll() throws IOException {

        List<PostOrRegion> regionList = postController.getAll();
        if (regionList==null)
            System.out.println("Файл пуст");
        else {
            System.out.println("Список постов:");
            for (PostOrRegion region : regionList) {
                System.out.println(region.getId() + "," + region.getContent());
            }
        }
    }

    public void viewUpdate(PostOrRegion post) throws IOException {

        PostOrRegion post1 = postController.Update(post);
        if (post1 != null)
            System.out.println("Идентификатор id=" + post.getId() + " теперь присвоен посту '" + post.getContent() + "'");
        else System.out.println("Ошибка:Пост не может быть изменен");
    }

    public void viewGetElementById(String str) throws IOException {

        PostOrRegion post = postController.getElementById(str);
        if (post != null)
            System.out.println("Идентификатор id=" + post.getId() + "принадлежит посту '" + post.getContent());
        else System.out.println("Пост не найден!");
    }

    public String routing() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("РАБОТА С ФАЙЛОМ  'post.txt':\n\n");
        System.out.println("Доступный список действий:\n ");
        System.out.println("1. Введите команду -'LIST'   чтобы получить список всех постов");
        System.out.println("2. Введите команду -'BYID'   чтобы получить пост по id ");
        System.out.println("3. Введите команду -'SAVE'   чтобы сохранить пост");
        System.out.println("4. Введите команду -'UPDATE' чтобы изменить пост");
        System.out.println("5. Введите команду -'DELETE' чтобы удалить  пост");
        System.out.println("6. Введите команду -'USER' для перехода к файлу 'user.json':");
        System.out.println("7. Введите команду -'REG' для перехода к файлу 'region.json':");

        String str = reader.readLine();
        switch (str) {
            case "LIST": {
                viewGetAll();
                break;
            }
            case "BYID": {
                System.out.println("Введите id: ");
                String id = reader.readLine();
                try {
                    Double.parseDouble(id);

                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели некорректный ID,повторите попытку");
                    break;
                }
                viewGetElementById(id);
                break;

            }
            case "SAVE": {
                System.out.println("Введите id: ");
                String id = reader.readLine();
                try {
                    Double.parseDouble(id);

                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели некорректный ID,повторите попытку");
                    break;
                }
                System.out.println("Введите Пост: ");
                String regionName = reader.readLine();
                PostOrRegion post = postOrRegion.createPostOrRegion("post");
                post.setId(id);
                post.setContent(regionName);
                viewSave(post);
                break;
            }
            case "UPDATE": {
                System.out.println("Введите id: ");
                String id = reader.readLine();
                System.out.println("Введите пост: ");
                String regionName = reader.readLine();
                PostOrRegion post = postOrRegion.createPostOrRegion("post");
                post.setId(id);
                post.setContent(regionName);
                viewUpdate(post);
                break;
            }
            case "DELETE": {
                System.out.println("Введите id: ");
                String id = reader.readLine();
                viewDeleteById(id);
                break;
            }
            case "USER":{
                return "USER";
            }
            case "REG":{
                return "REG";
            }
        }
        System.out.println("Введите 'POST' для перехода к файлу 'post.json':");
        System.out.println("Продолжить работу? Y/N :");
        str = reader.readLine();
        if (str.equals("N"))
            return "N";
        else return "Y";
    }
}

