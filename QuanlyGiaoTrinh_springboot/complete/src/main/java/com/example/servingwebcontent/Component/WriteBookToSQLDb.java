package com.example.servingwebcontent.Component;

import com.example.servingwebcontent.Database.BookInsertAiven;
import com.example.servingwebcontent.Model.Book;

public class WriteBookToSQLDb {
    public void writeToDb(Book b) {
        BookInsertAiven bi = new BookInsertAiven();
        bi.insertToAivenDb(b);
    }
}