package com.eugene.crude.crude.practic.controller;


import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.repository.hibernate.FileRepositoryImpl;
import com.eugene.crude.crude.practic.repository.hibernate.RegionRepositoryImpl;
import com.eugene.crude.crude.practic.repository.hibernate.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserRestControllerV1 extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String[] fileId = request.getParameterValues("fileId");
        String region = request.getParameter("region");

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setFirstName(name);
        user.setLasName(lastName);
        user.setRegion(regionController.getById(Integer.parseInt(region)));
        List<File> listFile = new ArrayList<>();
        for (String str : fileId) {
            listFile.add(fileController.getById(Integer.parseInt(str)));

        }
        user.setPosts(listFile);
        user = userController.update(user);
        printWriter.println("id= " + user.getId() + "first_name= " + user.getFirstName() + " last_name=" + user.getLasName() + "region= " + user.getRegion().getId());
        printWriter.println("Files_id: ");
        user.getPosts().stream().forEach(t -> printWriter.println(t.getId()));
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        userController.deleteById(Integer.parseInt(id));
        printWriter.println("DELETED...");

    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");


    }

    RegionRepositoryImpl regionController = new RegionRepositoryImpl();
    FileRepositoryImpl fileController = new FileRepositoryImpl();
    UserRepositoryImpl userController = new UserRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("all")) {
            List<User> userList = userController.getAll();
            printWriter.println("List User:");
            for (User e : userList) {
                printWriter.println("id= " + e.getId() + "first_name= " + e.getFirstName() + " last_name=" + e.getLasName() + "region= " + e.getRegion().getId());
                printWriter.println("Files_id: ");
                e.getPosts().stream().forEach(t -> printWriter.println(t.getId()));
            }
        }
        if (action.equals("byId")) {
            String id = request.getParameter("id");
            User user = userController.getById(Integer.parseInt(id));
            printWriter.println("id= " + user.getId() + "first_name= " + user.getFirstName() + " last_name=" + user.getLasName() + "region= " + user.getRegion().getId());
            printWriter.println("Files_id: ");
            user.getPosts().stream().forEach(t -> printWriter.println(t.getId()));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String[] fileId = request.getParameterValues("fileId");
        String region = request.getParameter("region");
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setFirstName(name);
        user.setLasName(lastName);
        user.setRegion(regionController.getById(Integer.parseInt(region)));
        List<File> listFile = new ArrayList<>();
        for (String str : fileId) {
            listFile.add(fileController.getById(Integer.parseInt(str)));

        }
        user.setPosts(listFile);
        user = userController.save(user);
        printWriter.println("id= " + user.getId() + "first_name= " + user.getFirstName() + " last_name=" + user.getLasName() + "region= " + user.getRegion().getId());
        printWriter.println("Files_id: ");
        user.getPosts().stream().forEach(t -> printWriter.println(t.getId()));

    }
}
