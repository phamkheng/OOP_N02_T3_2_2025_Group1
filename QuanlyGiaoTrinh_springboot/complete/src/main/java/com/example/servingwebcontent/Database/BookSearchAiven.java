package com.example.servingwebcontent.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Book;

public class BookSearchAiven {
    ArrayList<Book> items = new ArrayList<Book>();

    public ArrayList<Book> bookAivenSearch(String keyword) {
        try {
            myDBConnection my = new myDBConnection();
            Connection conn = my.getOnlyConn();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Books WHERE title LIKE ?");
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            System.out.println("Kết quả tìm kiếm sách với từ khóa: " + keyword);
            while (rs.next()) {
                Book book = new Book();
                book.bookID = rs.getString("bookID");
                book.title = rs.getString("title");
                book.author = rs.getString("author");
                book.isAvailable = rs.getBoolean("isAvailable");
                book.quantity = rs.getInt("quantity");
                System.out.println(book.bookID + " | " + book.title + " | " + book.author);
                items.add(book);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm kiếm sách: " + e);
            e.printStackTrace();
        }
        return items;
    }
}