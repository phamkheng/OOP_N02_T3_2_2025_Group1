package com.example.servingwebcontent.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Book;

public class WriteBookToFile {
    public void toFile(ArrayList<Book> books) {
        try {
            int i = books.size() - 1;
            FileWriter writer = new FileWriter("./complete/File/BookList.txt", true);
            writer.append("\n");
            writer.write(books.get(i).title);
            writer.write(books.get(i).author);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error at write to File!");
            e.printStackTrace();
        }
    }
}