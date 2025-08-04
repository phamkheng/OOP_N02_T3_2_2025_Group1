package com.example.servingwebcontent.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReaderDeleteAiven {
    public void deleteReaderFromDb(String readerID) {
        try {
            myDBConnection my = new myDBConnection();
            Connection conn = my.getOnlyConn();
            String sql = "DELETE FROM Readers WHERE readerID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, readerID);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã xóa độc giả với ID: " + readerID);
            } else {
                System.out.println("Không tìm thấy độc giả với ID: " + readerID);
            }
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa độc giả: " + e);
            e.printStackTrace();
        }
    }
}
