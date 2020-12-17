package com.eugene.crude.crude.practic.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBSConnection {
    public static JDBSConnection instance;
    public static final String PATH_TO_PROPERTIES = "src/main/resources/jdbc/jdbcConfig";
    public  static  final String JDBC_DRIVER="org.postgresql.Driver";
 public Connection connection;
    private JDBSConnection() {
    }
    public static JDBSConnection getInstance() {
        if (instance == null) {
                if (instance == null) {
                    instance = new JDBSConnection();
                }
            }return instance;
        }


    public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(PATH_TO_PROPERTIES));
        Class.forName(JDBC_DRIVER);
        String URL = properties.getProperty("URL");
        String USER = properties.getProperty("USER");
        String PASSWORD = properties.getProperty("PASSWORD");
        System.out.println("создантие коннекта к базе");
         connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Конект создан");

        return connection;

    }
}
