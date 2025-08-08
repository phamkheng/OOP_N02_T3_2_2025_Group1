package com.example.servingwebcontent.Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Reader;

public class LoanAiven {
    ArrayList<Loan> items = new ArrayList<Loan>();

    public ArrayList<Loan> loanAivenList() {
        try {
            myDBConnection my = new myDBConnection();
            Statement sta = my.getMyConn();

            String sql =
                "SELECT " +
                "l.loanID, l.borrowDate, l.dueDate, l.returnDate, l.status, " +
                "r.readerID, r.name AS readerName, r.email, r.phone, " +
                "b.bookID, b.title, b.author, b.status AS bookStatus " +
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

                String bookStatus = setdata.getString("bookStatus");
                book.isAvailable = bookStatus.equalsIgnoreCase("Available");

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
}
