package com.example.servingwebcontent.test;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Reader;
import com.example.servingwebcontent.service.Callback;
import com.example.servingwebcontent.service.LibraryManager;
import com.example.servingwebcontent.service.Interfaces.LibraryInterface;

public class TestCallback {
    public static void test() {
        LibraryInterface manager = new LibraryManager();

        Callback cb = new Callback(manager);

        Book   b1 = new Book("B001", "Clean Code", "Martin");    // sử dụng constructor phù hợp
        Reader r1 = new Reader("R001", "Nguyen Van A", "a@x.com", "0123");

        System.out.println("=== testObject(Book) ===");
        cb.testObject(b1);

        System.out.println("\n=== testObject(Reader) ===");
        cb.testObject(r1);

        

      
        System.out.println("\n=== test() ===");
        cb.test();
    }
}





