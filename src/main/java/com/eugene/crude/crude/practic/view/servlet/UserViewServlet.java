package com.eugene.crude.crude.practic.view.servlet;

import com.eugene.crude.crude.practic.controller.FileController;
import com.eugene.crude.crude.practic.controller.RegionController;
import com.eugene.crude.crude.practic.controller.UserController;
import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserViewServlet extends HttpServlet {
    UserController userController = new UserController();
    RegionController regionController = new RegionController();
    FileController fileController = new FileController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");


        switch (action) {
            case "getAll":

                List<User> userList = userController.getAll();
                printWriter.println("List User:");
                for (User e : userList) {
                    printWriter.println("id= " + e.getId() + "first_name= " + e.getFirstName() + " last_name=" + e.getLasName() + "region= " + e.getRegion().getId());
                    printWriter.println("Files_id: ");
                    e.getPosts().stream().forEach(t -> printWriter.println(t.getId()));

                }
                break;
            case "save": {
                String id = request.getParameter("id");
                String name = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String[] fileId = request.getParameterValues("fileId");
                String region = request.getParameter("region");
                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setFirstName(name);
                user.setLasName(lastName);
                user.setRegion(regionController.getElementById(region));
                List<File> listFile = new ArrayList<>();
                for (String str : fileId) {
                    listFile.add(fileController.getElementById(str));

                }
                user.setPosts(listFile);
                user = userController.save(user);
                printWriter.println("id= " + user.getId() + "first_name= " + user.getFirstName() + " last_name=" + user.getLasName() + "region= " + user.getRegion().getId());
                printWriter.println("Files_id: ");
                user.getPosts().stream().forEach(t -> printWriter.println(t.getId()));
                break;
            }
            case "update": {
                String id = request.getParameter("id");
                String name = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String[] fileId = request.getParameterValues("fileId");
                String region = request.getParameter("region");
                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setFirstName(name);
                user.setLasName(lastName);
                user.setRegion(regionController.getElementById(region));
                List<File> listFile = new ArrayList<>();
                for (String str : fileId) {
                    listFile.add(fileController.getElementById(str));

                }
                user.setPosts(listFile);
                user = userController.update(user);
                printWriter.println("id= " + user.getId() + "first_name= " + user.getFirstName() + " last_name=" + user.getLasName() + "region= " + user.getRegion().getId());
                printWriter.println("Files_id: ");
                user.getPosts().stream().forEach(t -> printWriter.println(t.getId()));
                break;
            }
            case "byid ": {
                String id = request.getParameter("id");

                User user = userController.getElementById(id);
                printWriter.println("id= " + user.getId() + "first_name= " + user.getFirstName() + " last_name=" + user.getLasName() + "region= " + user.getRegion().getId());
                printWriter.println("Files_id: ");
                user.getPosts().stream().forEach(t -> printWriter.println(t.getId()));

                break;
            }
            case "delete": {
                String id = request.getParameter("id");

                userController.deleteById(id);
                printWriter.println("DELETED...");

                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
