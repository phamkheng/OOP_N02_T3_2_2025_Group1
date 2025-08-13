package com.example.servingwebcontent.test;
import com.example.servingwebcontent.data.BookList;
import com.example.servingwebcontent.service.BookSearch;
public class TestBookSearch {
    public static void test() {
        BookList bookList = new BookList();
        BookSearch bookSearch = new BookSearch(bookList);

        bookSearch.searchBook();
    }
}