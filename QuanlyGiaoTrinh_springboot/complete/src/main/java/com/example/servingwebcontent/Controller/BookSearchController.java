package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Database.BookAiven;
import com.example.servingwebcontent.Model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookSearchController {
    @GetMapping("/searchBook")
    public String searchBook(
            @RequestParam String keyword,
            Model model) {
        
        BookAiven bookAiven = new BookAiven();
        List<Book> books = bookAiven.bookAivenSearch(keyword);
        model.addAttribute("books", books);
        return "booklist";
    }
}