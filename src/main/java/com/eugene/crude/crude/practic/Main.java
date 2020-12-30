package com.eugene.crude.crude.practic;

import com.eugene.crude.crude.practic.view.ViewHelper;

import java.io.IOException;
import java.sql.SQLException;


public class Main  {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
     ViewHelper viewHelper = new ViewHelper();
        viewHelper.mainHelper();
    }
}
