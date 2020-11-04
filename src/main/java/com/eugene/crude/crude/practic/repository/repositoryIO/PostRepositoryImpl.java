package com.eugene.crude.crude.practic.repository.repositoryIO;


import com.eugene.crude.crude.practic.model.Post;

import com.eugene.crude.crude.practic.model.PostOrRegion;
import com.eugene.crude.crude.practic.repository.IOUtils;
import com.eugene.crude.crude.practic.repository.PostRepository;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {


    private Path postsFilePath=Paths.get("C:\\javaFiles\\post.json"); ;
    private Gson gson = new Gson();



    @Override
    public  PostOrRegion getById(Long id) {
        try {
            List<String> listReg = Files.readAllLines(postsFilePath);
            if (!listReg.isEmpty()) {
                for (String str : listReg) {
                    if (str.contains("\"id\":\"" + id + "\"")) {
                        PostOrRegion post = gson.fromJson(str, Post.class);
                        return post;
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
    public List<PostOrRegion> getAll() {
        try {
            List<String> listReg = Files.readAllLines(postsFilePath);
            List<PostOrRegion> listRegionObj = new ArrayList<>();

            if (!listReg.isEmpty()) {
                for (String str : listReg) {
                    PostOrRegion post = gson.fromJson(str, Post.class);
                    listRegionObj.add(post);
                }
                return listRegionObj;
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public PostOrRegion save(PostOrRegion post) {
        try {
            if (!Files.exists(postsFilePath)) {
                Files.createFile(postsFilePath);
            }
            List<String> listReg = Files.readAllLines(postsFilePath);
            IOUtils ioUtils = new IOUtilsPostImpl();
            int validId = ioUtils.getValidId(listReg, Integer.parseInt(post.getId()));
            post.setId(String.valueOf(validId));
            try {
                String strToWrite = gson.toJson(post);
                Files.writeString(postsFilePath, strToWrite + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("ошибка записи в файл3");
            }
            return post;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public PostOrRegion update(PostOrRegion post) {
        try {
            List<String> listReg = Files.readAllLines(postsFilePath);

            String fdf = "\"id\":\"" + post.getId() + "\"";
            if (!listReg.isEmpty()) {
                for (int i = 0; i < listReg.size(); i++) {

                    if (listReg.get(i).contains(fdf)) {
                        PostOrRegion postNew = gson.fromJson(listReg.get(i), Post.class);
                        postNew.setContent(post.getContent());
                        listReg.set(i, gson.toJson(postNew));
                        break;
                    }
                }
                Files.delete(postsFilePath);
                Files.createFile(postsFilePath);
                for (String str : listReg) {
                    Files.writeString(postsFilePath, str + "\n", StandardOpenOption.APPEND);
                }
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            List<String> listReg = Files.readAllLines(postsFilePath);

            for (int i = 0; i < listReg.size(); i++) {
                if (listReg.get(i).contains(("\"id\":\"" + id + "\""))) {
                    listReg.set(i, "DELETE");
                }
            }
            Files.delete(postsFilePath);
            Files.createFile(postsFilePath);
            listReg.stream().forEach(str -> {
                if (!str.equals("DELETE")) {
                    try {
                        Files.writeString(postsFilePath, str + "\n", StandardOpenOption.APPEND);
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
