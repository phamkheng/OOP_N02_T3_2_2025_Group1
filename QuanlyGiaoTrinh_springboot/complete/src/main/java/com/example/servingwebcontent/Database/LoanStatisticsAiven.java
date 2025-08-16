package com.example.servingwebcontent.Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Reader;
import java.time.LocalDate;

public class LoanStatisticsAiven {
    
    // Lấy danh sách phiếu quá hạn
    public ArrayList<Loan> getOverdueLoans() {
        ArrayList<Loan> overdueLoans = new ArrayList<>();
        try {
            myDBConnection my = new myDBConnection();
            Statement sta = my.getMyConn();
            String currentDate = LocalDate.now().toString();
            
            ResultSet rs = sta.executeQuery("SELECT " +
                    "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
                    "r.readerID, r.name AS readerName, r.email, r.phone, " +
                    "b.bookID, b.title, b.author " +
                    "FROM Loans l " +
                    "JOIN Readers r ON l.readerID = r.readerID " +
                    "JOIN Books b ON l.bookID = b.bookID " +
                    "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
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
                overdueLoans.add(loan);
            }
            rs.close();
            sta.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
            e.printStackTrace();
        }
        return overdueLoans;
    }
    
    // Lấy lịch sử mượn sách của độc giả
    public ArrayList<Loan> getReaderHistory(String readerID) {
        ArrayList<Loan> history = new ArrayList<>();
        try {
            myDBConnection my = new myDBConnection();
            Statement sta = my.getMyConn();
            
            ResultSet rs = sta.executeQuery("SELECT " +
                    "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
                    "r.readerID, r.name AS readerName, r.email, r.phone, " +
                    "b.bookID, b.title, b.author " +
                    "FROM Loans l " +
                    "JOIN Readers r ON l.readerID = r.readerID " +
                    "JOIN Books b ON l.bookID = b.bookID " +
                    "WHERE l.readerID = '" + readerID + "' " +
                    "ORDER BY l.borrowDate DESC");
            
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
                history.add(loan);
            }
            rs.close();
            sta.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
            e.printStackTrace();
        }
        return history;
    }
    
    // Tìm kiếm phiếu mượn theo từ khóa
    public ArrayList<Loan> searchLoans(String keyword) {
        ArrayList<Loan> results = new ArrayList<>();
        try {
            myDBConnection my = new myDBConnection();
            Statement sta = my.getMyConn();
            
            ResultSet rs = sta.executeQuery("SELECT " +
                    "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
                    "r.readerID, r.name AS readerName, r.email, r.phone, " +
                    "b.bookID, b.title, b.author " +
                    "FROM Loans l " +
                    "JOIN Readers r ON l.readerID = r.readerID " +
                    "JOIN Books b ON l.bookID = b.bookID " +
                    "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
                    "r.name LIKE '%" + keyword + "%' OR " +
                    "b.title LIKE '%" + keyword + "%' OR " +
                    "l.status LIKE '%" + keyword + "%'");
            
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
                results.add(loan);
            }
            rs.close();
            sta.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
            e.printStackTrace();
        }
        return results;
    }
}
// // package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }
// package com.example.servingwebcontent.Database;

// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.util.ArrayList;
// // import com.example.servingwebcontent.Model.Loan;
// // import com.example.servingwebcontent.Model.Book;
// // import com.example.servingwebcontent.Model.Reader;
// // import java.time.LocalDate;

// // public class LoanStatisticsAiven {
    
// //     // Lấy danh sách phiếu quá hạn
// //     public ArrayList<Loan> getOverdueLoans() {
// //         ArrayList<Loan> overdueLoans = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
// //             String currentDate = LocalDate.now().toString();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.status = 'Borrowed' AND l.dueDate < '" + currentDate + "'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 overdueLoans.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy danh sách phiếu quá hạn: " + e);
// //             e.printStackTrace();
// //         }
// //         return overdueLoans;
// //     }
    
// //     // Lấy lịch sử mượn sách của độc giả
// //     public ArrayList<Loan> getReaderHistory(String readerID) {
// //         ArrayList<Loan> history = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.readerID = '" + readerID + "' " +
// //                     "ORDER BY l.borrowDate DESC");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 history.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi lấy lịch sử mượn trả: " + e);
// //             e.printStackTrace();
// //         }
// //         return history;
// //     }
    
// //     // Tìm kiếm phiếu mượn theo từ khóa
// //     public ArrayList<Loan> searchLoans(String keyword) {
// //         ArrayList<Loan> results = new ArrayList<>();
// //         try {
// //             myDBConnection my = new myDBConnection();
// //             Statement sta = my.getMyConn();
            
// //             ResultSet rs = sta.executeQuery("SELECT " +
// //                     "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
// //                     "r.readerID, r.name AS readerName, r.email, r.phone, " +
// //                     "b.bookID, b.title, b.author " +
// //                     "FROM Loans l " +
// //                     "JOIN Readers r ON l.readerID = r.readerID " +
// //                     "JOIN Books b ON l.bookID = b.bookID " +
// //                     "WHERE l.loanID LIKE '%" + keyword + "%' OR " +
// //                     "r.name LIKE '%" + keyword + "%' OR " +
// //                     "b.title LIKE '%" + keyword + "%' OR " +
// //                     "l.status LIKE '%" + keyword + "%'");
            
// //             while (rs.next()) {
// //                 Book book = new Book(
// //                         rs.getString("bookID"),
// //                         rs.getString("title"),
// //                         rs.getString("author")
// //                 );
// //                 Reader reader = new Reader(
// //                         rs.getString("readerID"),
// //                         rs.getString("readerName"),
// //                         rs.getString("email"),
// //                         rs.getString("phone")
// //                 );
// //                 Loan loan = new Loan(
// //                         rs.getString("loanID"),
// //                         book,
// //                         reader,
// //                         rs.getString("borrowDate"),
// //                         rs.getString("dueDate")
// //                 );
// //                 loan.returnDate = rs.getString("returnDate");
// //                 loan.status = rs.getString("status");
// //                 results.add(loan);
// //             }
// //             rs.close();
// //             sta.close();
// //         } catch (Exception e) {
// //             System.out.println("Lỗi khi tìm kiếm phiếu mượn: " + e);
// //             e.printStackTrace();
// //         }
// //         return results;
// //     }
// // }