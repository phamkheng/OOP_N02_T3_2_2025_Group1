package com.example.servingwebcontent.test;
import com.example.servingwebcontent.Controller.BookSearch;
import com.example.servingwebcontent.data.BookList;
public class TestBookSearch {
    public static void test() {
        BookList bookList = new BookList();
        BookSearch bookSearch = new BookSearch(bookList);

        bookSearch.searchBook();
    }
}