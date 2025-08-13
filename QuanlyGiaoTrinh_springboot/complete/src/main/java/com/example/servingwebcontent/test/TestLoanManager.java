package com.example.servingwebcontent.test;
import java.time.LocalDate;
import java.util.Scanner;

import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Reader;
import com.example.servingwebcontent.service.LibraryBook;
import com.example.servingwebcontent.service.LibraryReader;
import com.example.servingwebcontent.service.LoanManager;

public class TestLoanManager {
    public static void test(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        LibraryReader readerManager = new LibraryReader();
        LibraryBook bookManager = new LibraryBook();
        LoanManager loanManager = new LoanManager(readerManager, bookManager);
        bookManager.addBook(TestLibraryBook.b1);
        System.out.println("=== Danh sách sách có sẵn ===");
        bookManager.readBooks();
        System.out.println("\n=== Nhập thông tin người đọc ===");
        System.out.print("Nhập ID người đọc: ");
        String readerId = sc.nextLine();
        System.out.print("Nhập tên người đọc: ");
        String name = sc.nextLine();
        System.out.print("Nhập email: ");
        String email = sc.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = sc.nextLine();
        Reader newReader = new Reader(readerId, name, email, phone);
        readerManager.addReader(newReader);
        System.out.println("\n=== Nhập thông tin mượn sách ===");
        System.out.print("Nhập ID sách muốn mượn: ");
        String bookId = sc.nextLine();
        boolean bookFound = false;
        for (Book book : bookManager.getListBooks()) {
            if (book.bookID.equals(bookId)) {
                bookFound = true;
                if (book.quantity > 0) {
                    System.out.println("Sách còn " + book.quantity + " cuốn.");
                    System.out.println("Có thể mượn sách!");
                    String borrowDate = LocalDate.now().toString();
                    String returnDate = LocalDate.now().plusDays(30).toString();
                    String loanId = "L" + System.currentTimeMillis();
                    loanManager.createLoan(loanId, readerId, bookId, borrowDate, returnDate);
                    System.out.println("\n=== Thông tin phiếu mượn ===");
                    System.out.println("ID phiếu mượn: " + loanId);
                    System.out.println("Người mượn: " + name);
                    System.out.println("Sách mượn: " + book.title);
                    System.out.println("Ngày mượn: " + borrowDate);
                    System.out.println("Ngày trả: " + returnDate);
                } else {
                    System.out.println("Sách đã hết!");
                }
                break;
            }
        }
        if (!bookFound) {
            System.out.println("Không tìm thấy sách với ID: " + bookId);
        }
        sc.close();
    }
}