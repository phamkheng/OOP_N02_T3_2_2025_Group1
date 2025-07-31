package com.example.servingwebcontent.Component;

import java.util.ArrayList;
import com.example.servingwebcontent.Model.Book;

public class AddBookToList {
    public ArrayList<Book> addBookToList(Book b) {
        ArrayList<Book> al = new ArrayList<Book>();
        al.add(b);
        return al;
    }
}