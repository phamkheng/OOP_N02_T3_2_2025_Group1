package com.example.servingwebcontent.test;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.service.LibraryBook;
public class TestLibraryBook {
    public static Book b1 = new Book("B001", "OOP", "Viet Hung");
    public static void test() {

        LibraryBook libraryBook = new LibraryBook();
       
        Book b2 = new Book("B002", "Java", "Pham Khang");
        Book b3 = new Book("B003", "C++", "Nguyen Van C");

        libraryBook.addBook(b1);
        libraryBook.addBook(b2);
        libraryBook.addBook(b3);

        System.out.println("========== Danh sách sau khi thêm ==========");
        libraryBook.readBooks();

        System.out.println("========== Mượn sách B002 ==========");
        libraryBook.borrowBook("B002");

        System.out.println("========== Danh sách sách có sẵn ==========");
        libraryBook.showAvailableBooks();

        System.out.println("========== Tìm sách chứa 'java' ==========");
        libraryBook.findBookByTitle("java");

        System.out.println("========== Trả lại sách B002 ==========");
        libraryBook.returnBook("B002");

        System.out.println("========== Danh sách đầy đủ sau khi trả ==========");
        libraryBook.readBooks();

        System.out.println("========== Chỉnh sửa sách B003 ==========");
        libraryBook.editBook("B003");

        System.out.println("========== Danh sách sau khi chỉnh sửa ==========");
        libraryBook.readBooks();
    }

}