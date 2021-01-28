package com.eugene.crude.crude.practic.view.servlet;

import com.eugene.crude.crude.practic.controller.AccountController;
import com.eugene.crude.crude.practic.controller.FileController;
import com.eugene.crude.crude.practic.controller.RegionController;
import com.eugene.crude.crude.practic.controller.UserController;
import com.eugene.crude.crude.practic.model.Account;
import com.eugene.crude.crude.practic.model.AccountStatus;
import com.eugene.crude.crude.practic.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/Account")
public class AccountViewServlet extends HttpServlet {
    AccountController accountController = new AccountController();
    RegionController regionController = new RegionController();
    FileController fileController = new FileController();
    UserController userController = new UserController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");

        switch (action) {
            case "getAll":
                List<Account> userList = accountController.getAll();
                printWriter.println("List Account:");
                for (Account e : userList) {
                    printWriter.println("id= " + e.getId() + " user_id=" + e.getUser().getId() + " create_date= " + e.getDate() + " status= " + e.getAccountStatus());
                }
                break;
            case "save": {
                String id = request.getParameter("id");
                String userId = request.getParameter("userId");
                String date = request.getParameter("date");
                String status = request.getParameter("status");
                Account account = new Account();
                account.setId(Integer.parseInt(id));
                account.setUser(userController.getElementById(userId));
                DateFormat dateаFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date nDate1 = null;
                try {
                    nDate1 = dateаFormat.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                account.setDate(nDate1);
                account.setAccountStatus(AccountStatus.valueOf(status));
                account = accountController.save(account);
                printWriter.println("id= " + account.getId() + "user_id= " + account.getUser().getId() + " date=" + account.getDate() + "status= " + account.getAccountStatus().name());
                break;
            }
            case "update": {
                String id = request.getParameter("id");
                String userId = request.getParameter("userId");
                String date = request.getParameter("date");
                String status = request.getParameter("status");
                Account account = new Account();
                account.setId(Integer.parseInt(id));
                User user = userController.getElementById(userId);
                account.setUser(user);
                DateFormat dateаFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date nDate1 = null;

                try {
                    nDate1 = dateаFormat.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                account.setDate(nDate1);
                account.setAccountStatus(AccountStatus.valueOf(status));
                account = accountController.update(account);
                printWriter.println("id= " + account.getId() + "user_id= " + account.getUser().getId() + " date=" + account.getDate() + "status= " + account.getAccountStatus().name());
                break;
            }

            case "byid ": {
                String id = request.getParameter("id");
                Account account = accountController.getElementById(id);
                printWriter.println("id= " + account.getId() + "user_id= " + account.getUser().getId() + " date=" + account.getDate() + "status= " + account.getAccountStatus().name());
                break;
            }

            case "delete": {
                String id = request.getParameter("id");
                accountController.deleteById(id);
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