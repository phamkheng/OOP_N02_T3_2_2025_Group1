package com.example.servingwebcontent.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.example.servingwebcontent.Model.Reader;

public class ReaderUpdateAiven {
    public void updateReaderInDb(Reader reader) {
        try {
            myDBConnection my = new myDBConnection();
            Connection conn = my.getOnlyConn();
            String sql = "UPDATE Readers SET name = ?, email = ?, phone = ? WHERE readerID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, reader.name);
            pst.setString(2, reader.email);
            pst.setString(3, reader.phone);
            pst.setString(4, reader.readerID);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã cập nhật thông tin độc giả: " + reader.name);
            } else {
                System.out.println("Không tìm thấy độc giả với ID: " + reader.readerID);
            }
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật độc giả: " + e);
            e.printStackTrace();
        }
    }
}
