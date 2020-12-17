package com.eugene.crude.crude.practic.view;

import com.eugene.crude.crude.practic.utils.JDBSConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ViewHelper {
    String check="full";

   public void  mainHelper()  {
       ViewSetter viewSetter = new ViewSetter();
       JDBSConnection jdbsConnection = JDBSConnection.getInstance();
       try (Connection connection = jdbsConnection.getConnection()) {
           View userView = new UserView(connection);
           View postView = new PostView(connection);
           View regionView = new RegionView(connection);
           viewSetter.setView(userView);

           while (!check.equals("N")) {
               check = viewSetter.Do();
               switch (check) {
                   case "POST": {
                       viewSetter.setView(postView);
                       break;
                   }
                   case "REG": {
                       viewSetter.setView(regionView);
                       break;
                   }
                   case "USER": {
                       viewSetter.setView(userView);
                       break;
                   }
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
   }
}
