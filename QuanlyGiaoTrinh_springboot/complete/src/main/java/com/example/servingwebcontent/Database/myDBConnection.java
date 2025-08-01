package com.example.servingwebcontent.Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.Class;
import java.sql.Statement;


public class myDBConnection {

    public myDBConnection() {
    };

  
    String myDatabaseURL = "jdbc:mysql://avnadmin:AVNS_a0zLxZfb7rZFDoBLKIr@my-library-db-qlthuvien.b.aivencloud.com:28631/defaultdb?ssl-mode=REQUIRED";

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