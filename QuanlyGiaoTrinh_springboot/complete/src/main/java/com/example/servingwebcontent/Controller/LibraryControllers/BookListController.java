package com.example.servingwebcontent.Controller.LibraryControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.servingwebcontent.Database.BookAiven;
import com.example.servingwebcontent.Model.Book;

import java.util.ArrayList;

@Controller
public class BookListController {
    
    @GetMapping("/booklist")
    public String booklist(Model model) {
        
        ArrayList<Book> bookList = new ArrayList<Book>();
        BookAiven ba = new BookAiven();
        bookList = ba.bookAivenList();
        model.addAttribute("ListOfBooks", bookList);
        
        // data to View is ${ListOfBooks}
        // return view is booklist
        return "booklist";
    }
} 