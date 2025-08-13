package com.example.servingwebcontent.test;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Reader;
import com.example.servingwebcontent.service.ReturnProcess;

public class TestReturnProcess {

    public static void test() {
        Book b1 = new Book("B001", "OOP", "Viet Hung");
        Book b2 = new Book("B002", "Java", "Pham Khang");
        Book b3 = new Book("B003", "Python", "Nguyen Khoa");

        Reader r1 = new Reader("R001", "Hung", "a@gmail.com", "0123456789");
        Reader r2 = new Reader("R002", "Khang", "b@gmail.com", "0987654321");
        Reader r3 = new Reader("R003", "Khoa", "c@gmail.com", "0111222333");

        Loan l1 = new Loan("L001", b1, r1, "2025-07-15", "2025-07-20");
        Loan l2 = new Loan("L002", b2, r2, "2025-07-16", "2025-07-21");
        Loan l3 = new Loan("L003", b3, r3, "2025-07-17", "2025-07-22");

        ReturnProcess returnProcess = new ReturnProcess();
        returnProcess.addLoan(l1);
        returnProcess.addLoan(l2);
        returnProcess.addLoan(l3);

        System.out.println("========== Danh sách giao dịch ban đầu ==========");
        returnProcess.readLoans();

        System.out.println("========== Trả sách L002 ==========");
        returnProcess.returnBook("L002", "2025-07-20");

        System.out.println("========== Danh sách sau khi trả ==========");
        returnProcess.readLoans();

        System.out.println("========== Thử trả sách không tồn tại ==========");
        returnProcess.returnBook("L999", "2025-07-21");
    }
}
