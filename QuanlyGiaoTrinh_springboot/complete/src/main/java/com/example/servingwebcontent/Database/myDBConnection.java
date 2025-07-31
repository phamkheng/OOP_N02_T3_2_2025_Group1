package com.example.servingwebcontent.Database;

import org.springframework.stereotype.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.Class;
import java.sql.Statement;

@Controller
public class myDBConnection {

    public myDBConnection() {
    };

    // Đọc URL từ biến môi trường để tránh lộ secret
    String myDatabaseURL = System.getenv("MY_DATABASE_URL");

    String myDatabaseDriver = "com.mysql.cj.jdbc.Driver";

    public Connection conn = null;

    public Statement getMyConn() {

        try {

            Class.forName(myDatabaseDriver);
            conn = DriverManager.getConnection(myDatabaseURL);
            Statement sta = conn.createStatement();
            return sta;

        } catch (Exception e) {
            System.out.println("myDBConnection at 34" + e);
        }

        return null;

    }

    public Connection getOnlyConn() {
        try {
            Class.forName(myDatabaseDriver);

            conn = DriverManager.getConnection(myDatabaseURL);
            return conn;

        } catch (Exception e) {
            System.out.println("Database connection error: " + e);
        }

        return conn;

    }

}