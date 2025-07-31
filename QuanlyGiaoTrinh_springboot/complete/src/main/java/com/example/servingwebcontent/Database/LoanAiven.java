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
            ResultSet setdata = sta.executeQuery("SELECT * FROM Loans");
            System.out.println("Danh sách phiếu mượn:");
            while (setdata.next()) {
                Loan loan = new Loan(
                    setdata.getString("loanID"),
                    new Book(), // Có thể lấy thêm thông tin Book nếu cần
                    new Reader(), // Có thể lấy thêm thông tin Reader nếu cần
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