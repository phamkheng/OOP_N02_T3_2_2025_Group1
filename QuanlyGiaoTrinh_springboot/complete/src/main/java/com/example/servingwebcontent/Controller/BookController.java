package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import com.example.servingwebcontent.Component.AddBookToList;
import com.example.servingwebcontent.Component.ListBook;
import com.example.servingwebcontent.Component.WriteBookToFile;
import com.example.servingwebcontent.Component.WriteBookToSQLDb;
import com.example.servingwebcontent.Model.Book;

@Controller
public class BookController {

    @GetMapping("/addBook")
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam String bookID, Model model) {
        Book b = new Book();
        b.title = title;
        b.author = author;
        b.bookID = bookID;

        ArrayList<Book> al = new ArrayList<Book>();
        al.add(b);

        AddBookToList abl = new AddBookToList();
        abl.addBookToList(b);

        ListBook lb = new ListBook();
        lb.printListBook(al);

        WriteBookToFile wf = new WriteBookToFile();
        wf.toFile(al);

        WriteBookToSQLDb wdb = new WriteBookToSQLDb();
        wdb.writeToDb(b);

        model.addAttribute("book", b);
        return "bookadded"; // trả về view bookadded.html
    }
}