package com.eugene.crude.crude.practic.view.servlet;


import com.eugene.crude.crude.practic.controller.RegionController;
import com.eugene.crude.crude.practic.model.Region;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Region")
public class RegionViewServlet extends HttpServlet {
    RegionController regionController = new RegionController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");

        switch (action) {
            case "getAll":
                regionController = new RegionController();
                List<Region> regionList = regionController.getAll();
                printWriter.println("List Region:");
                regionList.stream().forEach(e -> printWriter.println("id= " + e.getId() + "region_name= " + e.getCharRegName()));
                break;
            case "save": {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                Region region = new Region();
                region.setId(Integer.parseInt(id));
                region.setCharRegName(name);
                region = regionController.save(region);
                printWriter.println("id= " + region.getId() + " region_name= " + region.getCharRegName());
                break;
            }
            case "update": {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                Region region = new Region();
                region.setId(Integer.parseInt(id));
                region.setCharRegName(name);
                regionController.update(region);
                printWriter.println("Update Region:");
                printWriter.println("id= " + region.getId() + " region_name= " + region.getCharRegName());
                break;
            }
            case "byid ": {
                String id = request.getParameter("id");

                Region region = regionController.getElementById(id);
                printWriter.println("id= " + region.getId() + " region_name= " + region.getCharRegName());

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
