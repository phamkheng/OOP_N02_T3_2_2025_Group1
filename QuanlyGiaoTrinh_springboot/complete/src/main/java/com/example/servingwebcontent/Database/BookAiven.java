package com.example.servingwebcontent.Database;
import com.example.servingwebcontent.Model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class BookAiven {
   
    private myDBConnection dbConn = new myDBConnection();
    
    
    public List<Book> bookAivenList() {
        List<Book> books = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
           
            System.out.println("Đang thực hiện truy vấn: SELECT * FROM Books ORDER BY bookID");
            
            stmt = dbConn.getMyConn();
            rs = stmt.executeQuery("SELECT * FROM Books ORDER BY bookID");
            
            System.out.println("Danh sách sách:");
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("bookID"),
                    rs.getString("title"),
                    rs.getString("author")
                );
                
               
                book.quantity = rs.getInt("quantity");
                
              
                if (rs.getBoolean("isAvailable")) {
                    book.markAsReturned();
                } else {
                    book.markAsBorrowed();
                }
                
                System.out.println(book.bookID + " | " + book.title + " | " + book.author + " | " + book.quantity);
                
                books.add(book);
            }
            
            System.out.println("Tổng số sách: " + books.size());
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách sách: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return books;
    }

    public List<Book> bookAivenSearch(String keyword) {
        List<Book> books = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = dbConn.getMyConn();
           
            String sql = "SELECT * FROM Books WHERE title LIKE '%" + keyword + "%' OR author LIKE '%" + keyword + "%'";
            
            System.out.println("Đang thực hiện truy vấn tìm kiếm: " + sql);
            
            rs = stmt.executeQuery(sql);
            
            System.out.println("Kết quả tìm kiếm cho '" + keyword + "':");
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("bookID"),
                    rs.getString("title"),
                    rs.getString("author")
                );
                
                book.quantity = rs.getInt("quantity");
                
                if (rs.getBoolean("isAvailable")) {
                    book.markAsReturned();
                } else {
                    book.markAsBorrowed();
                }
                
                System.out.println(book.bookID + " | " + book.title + " | " + book.author);
                
                books.add(book);
            }
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm sách: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return books;
    }
    

    public void insertToAivenDb(Book book) {
        Connection conn = null;
        PreparedStatement pst = null;
        
        try {
            
            conn = dbConn.getOnlyConn();
            String sql = "INSERT INTO Books (bookID, title, author, isAvailable, quantity) VALUES (?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, book.bookID);
            pst.setString(2, book.title);
            pst.setString(3, book.author);
            pst.setBoolean(4, book.isAvailable);
            pst.setInt(5, book.quantity);
            
            pst.executeUpdate();
            
            System.out.println("Đã thêm sách mới: " + book.title);
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm sách: " + e);
            e.printStackTrace();
            throw new RuntimeException("Error inserting book: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    
    public Book getBookById(String bookID) {
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            System.out.println("Đang tìm sách với ID: " + bookID);
            
            stmt = dbConn.getMyConn();
            // Sửa: Đúng tên bảng "Books"
            rs = stmt.executeQuery("SELECT * FROM Books WHERE bookID = '" + bookID + "'");
            
            if (rs.next()) {
                Book book = new Book(
                    rs.getString("bookID"),
                    rs.getString("title"),
                    rs.getString("author")
                );
                
                book.quantity = rs.getInt("quantity");
                
                if (rs.getBoolean("isAvailable")) {
                    book.markAsReturned();
                } else {
                    book.markAsBorrowed();
                }
                
                System.out.println("Tìm thấy sách: " + book.getTitle());
                
                return book;
            }
            
            System.out.println("Không tìm thấy sách với ID: " + bookID);
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm sách theo ID: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return null;
    }
    
    // Cập nhật sách
    public void updateBookInAivenDb(Book book) {
        Statement stmt = null;
        
        try {
            stmt = dbConn.getMyConn();
            
            String sql = "UPDATE Books SET " +
                       "title = '" + book.getTitle() + "', " +
                       "author = '" + book.getAuthor() + "', " +
                       "isAvailable = " + book.isAvailable() + ", " +
                       "quantity = " + book.quantity + " " +
                       "WHERE bookID = '" + book.getBookID() + "'";
            
            System.out.println("Đang thực hiện truy vấn: " + sql);
            
            stmt.executeUpdate(sql);
            
            System.out.println("Đã cập nhật sách: " + book.getBookID());
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật sách: " + e);
            e.printStackTrace();
            throw new RuntimeException("Error updating book: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    
    public void deleteBookFromAivenDb(String bookID) {
        Statement stmt = null;
        
        try {
            stmt = dbConn.getMyConn();
            
            String sql = "DELETE FROM Books WHERE bookID = '" + bookID + "'";
            
            System.out.println("Đang thực hiện truy vấn: " + sql);
            
            stmt.executeUpdate(sql);
            
            System.out.println("Đã xóa sách với ID: " + bookID);
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa sách: " + e);
            e.printStackTrace();
            throw new RuntimeException("Error deleting book: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        } 
    }
    
    public boolean updateBookInfo(String bookID, String title, String author, int quantity) {
        Statement stmt = null;
        
        try {
            stmt = dbConn.getMyConn();
            
        
            ResultSet rs = stmt.executeQuery("SELECT * FROM Books WHERE bookID = '" + bookID + "'");
            if (!rs.next()) {
                System.out.println("Book not found with ID: " + bookID);
                return false;
            }
            
            
            String sql = "UPDATE Books SET " +
                       "title = '" + title + "', " +
                       "author = '" + author + "', " +
                       "quantity = " + quantity + " " +
                       "WHERE bookID = '" + bookID + "'";
            
            System.out.println("Đang thực hiện truy vấn: " + sql);
            
            int rowsAffected = stmt.executeUpdate(sql);
            
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully: " + bookID);
                return true;
            } else {
                System.out.println("No book updated for ID: " + bookID);
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật thông tin sách: " + e);
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    public boolean updateBookQuantity(String bookID, int newQuantity) {
        Statement stmt = null;
        
        try {
            stmt = dbConn.getMyConn();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM Books WHERE bookID = '" + bookID + "'");
            if (!rs.next()) {
                System.out.println("Book not found with ID: " + bookID);
                return false;
            }
            
            
            String sql = "UPDATE Books SET quantity = " + newQuantity + " WHERE bookID = '" + bookID + "'";
            
            System.out.println("Đang thực hiện truy vấn: " + sql);
            
            int rowsAffected = stmt.executeUpdate(sql);
            
            if (rowsAffected > 0) {
                System.out.println("Book quantity updated successfully: " + bookID);
                return true;
            } else {
                System.out.println("No book quantity updated for ID: " + bookID);
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật số lượng sách: " + e);
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
}