package com.example.servingwebcontent.Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Book;

public class BookAiven {

    public void insertBook(Book book) {
        try {
            myDBConnection my = new myDBConnection();
            java.sql.Connection conn = my.getConn();
            
            String sql = "INSERT INTO Books (bookID, title, author, isAvailable, quantity) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBookID());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setBoolean(4, book.isAvailable());
            pstmt.setInt(5, book.getQuantity());
            
            pstmt.executeUpdate();
            
            System.out.println("Đã thêm thành công sách: " + book.getTitle());
            
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sách vào database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    ArrayList<Book> items = new ArrayList<Book>();

    public ArrayList<Book> bookAivenList() {
        try {
            myDBConnection my = new myDBConnection();
            Statement sta = my.getMyConn();
            ResultSet setdata = sta.executeQuery("SELECT * FROM Books");
            System.out.println("Danh sách sách trong thư viện:");
            while (setdata.next()) {
                Book book = new Book();
                book.bookID = setdata.getString("bookID");
                book.title = setdata.getString("title");
                book.author = setdata.getString("author");
                book.isAvailable = setdata.getBoolean("isAvailable");
                book.quantity = setdata.getInt("quantity");
                System.out.println(book.bookID + " | " + book.title + " | " + book.author);
                items.add(book);
            }
            setdata.close();
            sta.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách sách: " + e);
            e.printStackTrace();
        }
        return items;
    }
}