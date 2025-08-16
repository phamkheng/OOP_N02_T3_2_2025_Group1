package com.example.servingwebcontent.Database;
import java.sql.*;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.*;

public class LoanRepositoryAiven {
    
    public static void addLoan(Loan loan) {
        try {
            myDBConnection db = new myDBConnection();
            Statement stmt = db.getMyConn();
            String query = String.format(
                "INSERT INTO Loans (loanID, bookID, readerID, borrowDate, dueDate, returnDate, status) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                loan.loanID, loan.book.bookID, loan.reader.readerID, loan.borrowDate,
                loan.dueDate, loan.returnDate, loan.status
            );
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Loan> getAllLoans() {
        ArrayList<Loan> list = new ArrayList<>();
        try {
            myDBConnection db = new myDBConnection();
            Statement stmt = db.getMyConn();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Loans");
            while (rs.next()) {
                Loan loan = new Loan(
                    rs.getString("loanID"),
                    new Book(rs.getString("bookID"), "", ""),
                    new Reader(rs.getString("readerID"), "", "", ""),
                    rs.getString("borrowDate"),
                    rs.getString("dueDate")
                );
                loan.returnDate = rs.getString("returnDate");
                loan.status = rs.getString("status");
                list.add(loan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void deleteLoan(String loanID) {
        try {
            myDBConnection db = new myDBConnection();
            Statement stmt = db.getMyConn();
            stmt.executeUpdate("DELETE FROM Loans WHERE loanID='" + loanID + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateLoanStatus(String loanID, String returnDate) {
        try {
            myDBConnection db = new myDBConnection();
            Statement stmt = db.getMyConn();
            String query = String.format(
                "UPDATE Loans SET returnDate='%s', status='Returned' WHERE loanID='%s'",
                returnDate, loanID
            );
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static boolean checkBorrowCondition(String bookID, String readerID) {
        try {
            myDBConnection db = new myDBConnection();
            Statement stmt = db.getMyConn();
            
         
            ResultSet rs = stmt.executeQuery("SELECT * FROM Books WHERE bookID='" + bookID + "' AND isAvailable=true");
            if (!rs.next()) {
                rs.close();
                return false; 
            }
            rs.close();
            
            
            rs = stmt.executeQuery("SELECT * FROM Readers WHERE readerID='" + readerID + "'");
            if (!rs.next()) {
                rs.close();
                return false; 
            }
            rs.close();
            
       
            rs = stmt.executeQuery("SELECT COUNT(*) FROM Loans WHERE readerID='" + readerID + "' AND status='Borrowed'");
            if (rs.next() && rs.getInt(1) >= 5) {
                rs.close();
                return false; 
            }
            rs.close();
            
            return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
//âsasa