package com.example.servingwebcontent.Component;

import com.example.servingwebcontent.Database.BookAiven;
import com.example.servingwebcontent.Model.Book;

public class WriteBookToSQLDb {
    public void writeToDb(Book book) {
        BookAiven bookAiven = new BookAiven();
        bookAiven.insertToAivenDb(book);
    }
}