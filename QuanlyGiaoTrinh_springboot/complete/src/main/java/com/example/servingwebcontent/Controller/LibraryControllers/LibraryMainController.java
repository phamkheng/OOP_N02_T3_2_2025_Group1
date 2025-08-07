package com.example.servingwebcontent.Controller.LibraryControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.Database.BookInsertAiven;
import com.example.servingwebcontent.Model.Book;

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

@GetMapping({"/add-Book", "/add-book"})
public String addBookForm() {
    return "add-book";
}

@PostMapping({"/add-Book", "/add-book"})
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
    return "add-book";
    }
}