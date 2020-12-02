package com.eugene.crude.crude.practic;

import com.eugene.crude.crude.practic.factory.FactoryImpl.UserFactoryImpl;
import com.eugene.crude.crude.practic.factory.UserFactory;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.repository.bdRepositoty.UserRepositoryImpl;
import com.eugene.crude.crude.practic.view.ViewHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main  {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
       // Path file = Paths.get("C:\\javaFiles\\posts.json");





     ViewHelper viewHelper = new ViewHelper();
        viewHelper.mainHelper();

    }
}
