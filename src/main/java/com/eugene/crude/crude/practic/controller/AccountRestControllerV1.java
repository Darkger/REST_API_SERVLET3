package com.eugene.crude.crude.practic.controller;
import com.eugene.crude.crude.practic.model.Account;
import com.eugene.crude.crude.practic.model.AccountStatus;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.repository.hibernate.AccountRepositoryImpl;
import com.eugene.crude.crude.practic.repository.hibernate.FileRepositoryImpl;
import com.eugene.crude.crude.practic.repository.hibernate.RegionRepositoryImpl;
import com.eugene.crude.crude.practic.repository.hibernate.UserRepositoryImpl;
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

@WebServlet("/api/v1/accounts")
public class AccountRestControllerV1 extends HttpServlet {
    AccountRepositoryImpl accountController = new AccountRepositoryImpl();
    RegionRepositoryImpl regionController = new RegionRepositoryImpl();
    FileRepositoryImpl fileController = new FileRepositoryImpl();
    UserRepositoryImpl userController = new UserRepositoryImpl();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        Account account = new Account();
        account.setId(Integer.parseInt(id));
        User user = userController.getById(Integer.parseInt(userId));
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

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        accountController.getById(Integer.parseInt(id));
        printWriter.println("DELETED...");
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        if(action.equals("all")){
        List<Account> userList = accountController.getAll();
        printWriter.println("List Account:");
        for (Account e : userList) {
            printWriter.println("id= " + e.getId() + " user_id=" + e.getUser().getId() + " create_date= " + e.getDate() + " status= " + e.getAccountStatus());
        }}
if(action.equals("byId")){

    String id = request.getParameter("id");
    Account account = accountController.getById(Integer.parseInt(id));
    printWriter.println("id= " + account.getId() + "user_id= " + account.getUser().getId() + " date=" + account.getDate() + "status= " + account.getAccountStatus().name());
}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");
        PrintWriter printWriter = response.getWriter();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        Account account = new Account();
        account.setId(Integer.parseInt(id));
        account.setUser(userController.getById(Integer.parseInt(userId)));
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

    }
}