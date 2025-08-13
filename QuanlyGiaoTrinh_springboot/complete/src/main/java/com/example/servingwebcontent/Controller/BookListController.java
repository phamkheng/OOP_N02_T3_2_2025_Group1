package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Database.BookAiven;
import com.example.servingwebcontent.Model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class BookListController {
    @GetMapping("/booklist")
    public String booklist(Model model) {
        try {
            BookAiven bookAiven = new BookAiven();
            List<Book> books = bookAiven.bookAivenList();
            model.addAttribute("ListOfBooks", books); 
            return "booklist";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading books: " + e.getMessage());
            return "booklist";
        }
    }
}