package com.example.servingwebcontent.Controller.LibraryControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.Database.BookSearchAiven;
import com.example.servingwebcontent.Model.Book;

import java.util.ArrayList;

@Controller
public class BookSearchController {

    @GetMapping("/searchbook")
    public String bookSearchList(Model model, @RequestParam String keyword) {
        
        ArrayList<Book> bookList = new ArrayList<Book>();
        BookSearchAiven bsa = new BookSearchAiven();
        
        bookList = bsa.bookAivenSearch(keyword);
        model.addAttribute("ListOfBooks", bookList);
        
        // data to View is ${ListOfBooks}
        // return view is booklist
        return "booklist";
    }

    @GetMapping("/getSearchBook")
    public String bookSearchForm(Model model) {
        return "searchbook";
    }
} 