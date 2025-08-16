package com.example.servingwebcontent.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Reader;

public class LoanSearchAiven {
    ArrayList<Loan> items = new ArrayList<Loan>();
    
    
    public ArrayList<Loan> searchLoans(String keyword, String status, String readerID) {
        items.clear();
        try {
            myDBConnection my = new myDBConnection();
            String sql = "SELECT " +
                    "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
                    "r.readerID, r.name AS readerName, r.email, r.phone, " +
                    "b.bookID, b.title, b.author " +
                    "FROM Loans l " +
                    "JOIN Readers r ON l.readerID = r.readerID " +
                    "JOIN Books b ON l.bookID = b.bookID " +
                    "WHERE 1=1";
            
            // Thêm điều kiện tìm kiếm
            if (keyword != null && !keyword.isEmpty()) {
                sql += " AND (l.loanID LIKE '%" + keyword + "%' OR " +
                        "r.name LIKE '%" + keyword + "%' OR " +
                        "b.title LIKE '%" + keyword + "%')";
            }
            
            if (status != null && !status.isEmpty()) {
                sql += " AND l.status = '" + status + "'";
            }
            
            if (readerID != null && !readerID.isEmpty()) {
                sql += " AND l.readerID = '" + readerID + "'";
            }
            
            PreparedStatement pst = my.getOnlyConn().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("bookID"),
                        rs.getString("title"),
                        rs.getString("author")
                );
                Reader reader = new Reader(
                        rs.getString("readerID"),
                        rs.getString("readerName"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                Loan loan = new Loan(
                        rs.getString("loanID"),
                        book,
                        reader,
                        rs.getString("borrowDate"),
                        rs.getString("dueDate")
                );
                loan.returnDate = rs.getString("returnDate");
                loan.status = rs.getString("status");
                items.add(loan);
            }
            
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
            e.printStackTrace();
        }
        return items;
    }
}
//abc