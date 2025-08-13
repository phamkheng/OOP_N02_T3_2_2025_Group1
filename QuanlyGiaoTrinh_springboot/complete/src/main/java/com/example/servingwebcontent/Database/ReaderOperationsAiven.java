package com.example.servingwebcontent.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Reader;
public class ReaderOperationsAiven {
    ArrayList<Reader> items = new ArrayList<Reader>();

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
    
   
    public ArrayList<Reader> readerAivenSearch(String keyword) {
        items.clear();
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
    

    public Reader getReaderById(String readerID) {
        try {
            myDBConnection my = new myDBConnection();
            Connection conn = my.getOnlyConn();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Readers WHERE readerID = ?");
            pst.setString(1, readerID);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                Reader reader = new Reader();
                reader.readerID = rs.getString("readerID");
                reader.name = rs.getString("name");
                reader.email = rs.getString("email");
                reader.phone = rs.getString("phone");
                rs.close();
                pst.close();
                conn.close();
                return reader;
            }
            
            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm độc giả theo ID: " + e);
            e.printStackTrace();
        }
        return null;
    }
}