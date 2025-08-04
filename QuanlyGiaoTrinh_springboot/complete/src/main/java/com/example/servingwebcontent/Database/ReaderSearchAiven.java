package com.example.servingwebcontent.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Reader;

public class ReaderSearchAiven {
    ArrayList<Reader> items = new ArrayList<Reader>();

    public ArrayList<Reader> readerAivenSearch(String keyword) {
        try {
            myDBConnection my = new myDBConnection();
            Connection conn = my.getOnlyConn();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Readers WHERE name LIKE ? OR email LIKE ? OR phone LIKE ?");
            String searchPattern = "%" + keyword + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            ResultSet rs = pst.executeQuery();
            System.out.println("Kết quả tìm kiếm độc giả với từ khóa: " + keyword);
            while (rs.next()) {
                Reader reader = new Reader();
                reader.readerID = rs.getString("readerID");
                reader.name = rs.getString("name");
                reader.email = rs.getString("email");
                reader.phone = rs.getString("phone");
                System.out.println(reader.readerID + " | " + reader.name + " | " + reader.email);
                items.add(reader);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm kiếm độc giả: " + e);
            e.printStackTrace();
        }
        return items;
    }
}
