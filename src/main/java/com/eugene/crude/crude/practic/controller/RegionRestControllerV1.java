package com.eugene.crude.crude.practic.controller;


import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.repository.hibernate.RegionRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/v1/regions")
public class RegionRestControllerV1 extends HttpServlet {
    RegionRepositoryImpl regionController = new RegionRepositoryImpl();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Region region = new Region();
        region.setId(Integer.parseInt(id));
        region.setCharRegName(name);
        regionController.update(region);
        printWriter.println("Update Region:");
        printWriter.println("id= " + region.getId() + " region_name= " + region.getCharRegName());


    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        regionController.deleteById(Integer.parseInt(id));
        printWriter.println("DELETED...");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");

        if (action.equals("all")) {
            List<Region> regionList = regionController.getAll();
            printWriter.println("List Region:");
            regionList.stream().forEach(e -> printWriter.println("id= " + e.getId() + "region_name= " + e.getCharRegName()));
        }
        if (action.equals("byId")) {
            String id = request.getParameter("id");

            Region region = regionController.getById(Integer.parseInt(id));
            printWriter.println("id= " + region.getId() + " region_name= " + region.getCharRegName());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Region region = new Region();
        region.setId(Integer.parseInt(id));
        region.setCharRegName(name);
        region = regionController.save(region);
        printWriter.println("id= " + region.getId() + " region_name= " + region.getCharRegName());
    }
}
