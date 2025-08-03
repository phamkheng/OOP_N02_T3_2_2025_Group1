package com.example.servingwebcontent.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.example.servingwebcontent.Model.Book;

public class BookInsertAiven {
    public void insertToAivenDb(Book book) {
        try {
            myDBConnection my = new myDBConnection();
            Connection conn = my.getOnlyConn();
            String sql = "INSERT INTO Books (bookID, title, author, isAvailable, quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, book.bookID);
            pst.setString(2, book.title);
            pst.setString(3, book.author);
            pst.setBoolean(4, book.isAvailable);
            pst.setInt(5, book.quantity);
            pst.executeUpdate();
            System.out.println("Đã thêm sách mới: " + book.title);
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sách: " + e);
            e.printStackTrace();
        }
    }
}