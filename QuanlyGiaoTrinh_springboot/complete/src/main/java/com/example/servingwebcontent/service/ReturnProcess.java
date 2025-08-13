package com.example.servingwebcontent.service;


import java.util.ArrayList;

import com.example.servingwebcontent.Model.Loan;

public class ReturnProcess {

    ArrayList<Loan> listLoans = new ArrayList<>();
     public ReturnProcess() {
        this.listLoans = new ArrayList<>();
    }

    public ArrayList<Loan> addLoan(Loan l) {
        try {
            listLoans.add(l);
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm giao dịch: " + e.getMessage());
        }
        return listLoans;
    }

    public void readLoans() {
        try {
            for (Loan l : listLoans) {
                System.out.println("Loan ID: " + l.loanID);
                System.out.println("Book: " + l.book.getTitle());
                System.out.println("Reader: " + l.reader.name);
                System.out.println("Loan Date: " + l.borrowDate);
                System.out.println("Return Date: " + (l.returnDate != null ? l.returnDate : "Chưa trả"));
                System.out.println("Status: " + l.status);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc danh sách giao dịch: " + e.getMessage());
        }
    }

    public ArrayList<Loan> returnBook(String loanId, String returnDate) {
        try {
            for (Loan l : listLoans) {
                if (l.loanID.equals(loanId)) {
                    if (l.status.equals("Returned")) {
                        System.out.println("Giao dịch này đã được trả trước đó.");
                        return listLoans;
                    }
                    l.markReturned(returnDate);
                    l.reader.returnBook(l);
                    System.out.println("Đã trả sách thành công.");
                    return listLoans;
                }
            }
            System.out.println("Không tìm thấy giao dịch với ID đã nhập.");
        } catch (Exception e) {
            System.out.println("Lỗi khi trả sách: " + e.getMessage());
        }
        return listLoans;
    }
}
