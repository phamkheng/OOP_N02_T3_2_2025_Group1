package com.example.servingwebcontent.Component;

import java.util.ArrayList;
import com.example.servingwebcontent.Model.Book;

public class ListBook {
    public void printListBook(ArrayList<Book> listBook) {
        for (int i = 0; i < listBook.size(); i++) {
            System.out.println("List book:");
            System.out.println(listBook.get(i).title);
            System.out.println(listBook.get(i).author);
            System.out.println(listBook.get(i).bookID);
        }
    }
}