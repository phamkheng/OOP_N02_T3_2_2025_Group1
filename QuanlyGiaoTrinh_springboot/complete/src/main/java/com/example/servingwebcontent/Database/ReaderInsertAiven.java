package com.example.servingwebcontent.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.example.servingwebcontent.Model.Reader;

public class ReaderInsertAiven {
    public void insertToAivenDb(Reader reader) {
        try {
            myDBConnection my = new myDBConnection();
            Connection conn = my.getOnlyConn();
            String sql = "INSERT INTO Readers (readerID, name, email, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, reader.readerID);
            pst.setString(2, reader.name);
            pst.setString(3, reader.email);
            pst.setString(4, reader.phone);
            pst.executeUpdate();
            System.out.println("Đã thêm độc giả mới: " + reader.name);
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm độc giả: " + e);
            e.printStackTrace();
        }
    }
}
