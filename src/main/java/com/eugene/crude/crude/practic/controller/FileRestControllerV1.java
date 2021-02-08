package com.eugene.crude.crude.practic.controller;


import com.eugene.crude.crude.practic.model.File;
import com.eugene.crude.crude.practic.repository.hibernate.FileRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/api/v1/files")
public class FileRestControllerV1 extends HttpServlet {
    FileRepositoryImpl regionController = new FileRepositoryImpl();
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        File file = new File();
        file.setId(Integer.parseInt(id));
        file.setName(name);
        regionController.update(file);
        printWriter.println("Update Region:");
        printWriter.println("id= " + file.getId() + " region_name= " + file.getName());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        regionController.deleteById(Integer.parseInt(id));
        printWriter.println("DELETED...");
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        File file = regionController.getById(Integer.parseInt(id));
        printWriter.println("id= " + file.getId() + " region_name= " + file.getName());
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        if(action.equals("all")){
        List<File> regionList = regionController.getAll();
        printWriter.println("List Files:");
        regionList.stream().forEach(e -> printWriter.println("id= " + e.getId() + "Files_name= " + e.getName()));}
        if(action.equals("byId")){
            String id = request.getParameter("id");

            File file = regionController.getById(Integer.parseInt(id));
            printWriter.println("id= " + file.getId() + " region_name= " + file.getName());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        File file = new File();
        file.setId(Integer.parseInt(id));
        file.setName(name);
        file = regionController.save(file);
        printWriter.println("id= " + file.getId() + " region_name= " + file.getName());

    }
}
