package com.eugene.crude.crude.practic.utils;
import  java.sql.*;

public class BfConnection {
    static final String JDBC_DRIVER = "org.postgresql.Driver";

public Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName(JDBC_DRIVER);
    String URL = "jdbc:postgresql://127.0.0.1/new_db";
    String USER = "webtester";
    String PASSWORD = "1234";
    System.out.println("создантие коннекта к базе");
    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    System.out.println("Конект создан");

    return connection;

}
}
