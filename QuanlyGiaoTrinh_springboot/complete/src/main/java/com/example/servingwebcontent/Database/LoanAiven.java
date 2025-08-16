package com.example.servingwebcontent.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Reader;

public class LoanAiven {
    ArrayList<Loan> items = new ArrayList<Loan>();
    
    public ArrayList<Loan> loanAivenList() {
        items.clear();
        try {
            myDBConnection my = new myDBConnection();
            Statement sta = my.getMyConn();
            String sql = "SELECT " +
                    "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
                    "r.readerID, r.name AS readerName, r.email, r.phone, " +
                    "b.bookID, b.title, b.author " +
                    "FROM Loans l " +
                    "JOIN Readers r ON l.readerID = r.readerID " +
                    "JOIN Books b ON l.bookID = b.bookID";
            ResultSet setdata = sta.executeQuery(sql);
            System.out.println("Danh sách phiếu mượn:");
            while (setdata.next()) {
                Book book = new Book(
                        setdata.getString("bookID"),
                        setdata.getString("title"),
                        setdata.getString("author")
                );
                book.isAvailable = true;
                Reader reader = new Reader(
                        setdata.getString("readerID"),
                        setdata.getString("readerName"),
                        setdata.getString("email"),
                        setdata.getString("phone")
                );
                Loan loan = new Loan(
                        setdata.getString("loanID"),
                        book,
                        reader,
                        setdata.getString("borrowDate"),
                        setdata.getString("dueDate")
                );
                loan.returnDate = setdata.getString("returnDate");
                loan.status = setdata.getString("status");
                System.out.println(loan.loanID + " | " + loan.borrowDate + " | " + loan.status);
                items.add(loan);
            }
            setdata.close();
            sta.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách phiếu mượn: " + e);
            e.printStackTrace();
        }
        return items;
    }
    
    public void insertLoan(Loan loan) {
        try {
            myDBConnection my = new myDBConnection();
            String sql = "INSERT INTO Loans (loanID, readerID, bookID, borrowDate, dueDate, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = my.getOnlyConn().prepareStatement(sql);
            ps.setString(1, loan.loanID);
            ps.setString(2, loan.getReader().readerID);
            ps.setString(3, loan.book.bookID);
            ps.setString(4, loan.borrowDate);
            ps.setString(5, loan.dueDate);
            ps.setString(6, loan.status != null ? loan.status : "Borrowed");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm phiếu mượn: " + e);
            e.printStackTrace();
        }
    }
    
    public void updateLoan(Loan loan) {
        try {
            myDBConnection my = new myDBConnection();
            String sql = "UPDATE Loans SET readerID=?, bookID=?, borrowDate=?, dueDate=?, returnDate=?, status=? WHERE loanID=?";
            PreparedStatement ps = my.getOnlyConn().prepareStatement(sql);
            ps.setString(1, loan.getReader().readerID);
            ps.setString(2, loan.book.bookID);
            ps.setString(3, loan.borrowDate);
            ps.setString(4, loan.dueDate);
            ps.setString(5, loan.returnDate);
            ps.setString(6, loan.status);
            ps.setString(7, loan.loanID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật phiếu mượn: " + e);
        }
    }
    
    public void deleteLoan(String id) {
        try {
            myDBConnection my = new myDBConnection();
            String sql = "DELETE FROM Loans WHERE loanID=?";
            PreparedStatement ps = my.getOnlyConn().prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa phiếu mượn: " + e);
        }
    }
    
    
    public Loan getLoanById(String loanID) {
        try {
            myDBConnection my = new myDBConnection();
            Statement sta = my.getMyConn();
            ResultSet setdata = sta.executeQuery("SELECT " +
                    "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
                    "r.readerID, r.name AS readerName, r.email, r.phone, " +
                    "b.bookID, b.title, b.author " +
                    "FROM Loans l " +
                    "JOIN Readers r ON l.readerID = r.readerID " +
                    "JOIN Books b ON l.bookID = b.bookID " +
                    "WHERE l.loanID = '" + loanID + "'");
            
            if (setdata.next()) {
                Book book = new Book(
                        setdata.getString("bookID"),
                        setdata.getString("title"),
                        setdata.getString("author")
                );
                book.isAvailable = true;
                Reader reader = new Reader(
                        setdata.getString("readerID"),
                        setdata.getString("readerName"),
                        setdata.getString("email"),
                        setdata.getString("phone")
                );
                Loan loan = new Loan(
                        setdata.getString("loanID"),
                        book,
                        reader,
                        setdata.getString("borrowDate"),
                        setdata.getString("dueDate")
                );
                loan.returnDate = setdata.getString("returnDate");
                loan.status = setdata.getString("status");
                
                setdata.close();
                sta.close();
                return loan;
            }
            
            setdata.close();
            sta.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm phiếu mượn theo ID: " + e);
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void updateLoanStatus(String loanID, String returnDate) {
        try {
            myDBConnection my = new myDBConnection();
            String sql = "UPDATE Loans SET returnDate=?, status='Returned' WHERE loanID=?";
            PreparedStatement ps = my.getOnlyConn().prepareStatement(sql);
            ps.setString(1, returnDate);
            ps.setString(2, loanID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật trạng thái mượn trả: " + e);
            e.printStackTrace();
        }
    }
}
