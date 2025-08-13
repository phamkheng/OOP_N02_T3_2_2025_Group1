package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.servingwebcontent.test.TestBookSearch;
import com.example.servingwebcontent.test.TestLibraryBook;
import com.example.servingwebcontent.test.TestLibraryLoan;
import com.example.servingwebcontent.test.TestLibraryReader;
import com.example.servingwebcontent.test.TestLoanManager;
import com.example.servingwebcontent.test.TestRegisterReader;
import com.example.servingwebcontent.test.TestReturnProcess;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);

        new Thread(() -> {
            try {
                System.out.println("=== Chạy TEST ===");
                TestBookSearch.test();
                TestLoanManager.test(args);
                TestRegisterReader.test();
                TestReturnProcess.test();
                TestLibraryBook.test();
                TestLibraryReader.test();
                TestLibraryLoan.test();
                System.out.println("=== KẾT THÚC TEST ===");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}