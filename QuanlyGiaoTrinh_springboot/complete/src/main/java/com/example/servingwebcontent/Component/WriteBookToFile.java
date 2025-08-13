package com.example.servingwebcontent.Component;

import com.example.servingwebcontent.Model.Book;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteBookToFile {
    public void toFile(Book book) {
        try {
          
            FileWriter writer = new FileWriter("booklist.txt", true); 
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            
            bufferedWriter.write(book.bookID + "," + book.title + "," + book.author + "," + 
                              book.isAvailable + "," + book.quantity);
            bufferedWriter.newLine();
            
            bufferedWriter.close();
            System.out.println("Đã ghi sách vào file: " + book.title);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}