package com.example.servingwebcontent.Controller.LibraryControllers;

import com.example.servingwebcontent.Database.BookAiven;
import com.example.servingwebcontent.Model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class BookListController {

    private final BookAiven bookAiven = new BookAiven();

    @GetMapping("/booklist")
    public String booklist(Model model) {
        ArrayList<Book> bookList = bookAiven.bookAivenList();
        model.addAttribute("ListOfBooks", bookList);
        return "booklist";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute("book") Book book) {
        String uniqueID = "S" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        book.setBookID(uniqueID);
        book.setAvailable(true);
        bookAiven.insertBook(book);
        return "redirect:/booklist";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") String id) {
        bookAiven.deleteBookById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/edit-book/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Book book = bookAiven.findBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookAiven.updateBook(book);
        return "redirect:/booklist";
    }
}