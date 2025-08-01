package com.example.servingwebcontent.Controller.LibraryControllers;

import com.example.servingwebcontent.Database.BookAiven;
import com.example.servingwebcontent.Model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class LibraryMainController {

    @GetMapping({"/", "/index"})
    public String homePage(Model model) {
        model.addAttribute("book", new Book());
        return "index";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        String uniqueID = "S" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        book.setBookID(uniqueID);
        book.setAvailable(true);

        BookAiven bookDb = new BookAiven();
        bookDb.insertBook(book);

        return "redirect:/booklist";
    }
}
@Controller
public class LibraryMainController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/library")
    public String library(Model model) {
        return "library";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam String bookID, 
                         @RequestParam String title, 
                         @RequestParam String author, 
                         @RequestParam(defaultValue = "true") boolean isAvailable,
                         @RequestParam(defaultValue = "1") int quantity, 
                         Model model) {
        
        try {
            Book book = new Book(bookID, title, author);
            book.isAvailable = isAvailable;
            book.quantity = quantity;
            
            BookInsertAiven bia = new BookInsertAiven();
            bia.insertToAivenDb(book);
            
            model.addAttribute("message", "Thêm sách thành công!");
            model.addAttribute("book", book);
            
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm sách: " + e.getMessage());
        }
        
        return "addbook";
    }
} 