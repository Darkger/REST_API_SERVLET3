package com.eugene.crude.crude.practic.view.servlet;

import com.eugene.crude.crude.practic.controller.FileController;
import com.eugene.crude.crude.practic.model.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileViewServlet extends HttpServlet {
    FileController regionController = new FileController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");

        switch (action) {
            case "getAll":

                List<File> regionList = regionController.getAll();
                printWriter.println("List Files:");
                regionList.stream().forEach(e -> printWriter.println("id= " + e.getId() + "Files_name= " + e.getName()));
                break;
            case "save": {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                File file = new File();
                file.setId(Integer.parseInt(id));
                file.setName(name);
                file = regionController.save(file);
                printWriter.println("id= " + file.getId() + " region_name= " + file.getName());
                break;
            }
            case "update": {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                File file = new File();
                file.setId(Integer.parseInt(id));
                file.setName(name);
                regionController.update(file);
                printWriter.println("Update Region:");
                printWriter.println("id= " + file.getId() + " region_name= " + file.getName());
                break;
            }
            case "byid ": {
                String id = request.getParameter("id");

                File file = regionController.getElementById(id);
                printWriter.println("id= " + file.getId() + " region_name= " + file.getName());

                break;
            }
            case "delete": {
                String id = request.getParameter("id");

                regionController.deleteById(id);
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
