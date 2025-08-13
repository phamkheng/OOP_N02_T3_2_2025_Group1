package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Component.WriteBookToFile;
import com.example.servingwebcontent.Database.BookAiven;
import com.example.servingwebcontent.Model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LibraryBookController {
    
   
    @GetMapping("/add-book")
    public String addBookForm() {
        return "add-book";
    }
    
    @PostMapping("/addBook")
    public String addBook(
            @RequestParam String bookID,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String quantity,
            @RequestParam(required = false) Boolean isAvailable,
            RedirectAttributes redirectAttributes) {
        
        try {
          
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Author cannot be empty");
            }
            if (quantity == null || Integer.parseInt(quantity) <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0");
            }
            
           
            Book book = new Book(bookID, title, author);
            book.quantity = (Integer.parseInt(quantity));
            
            
            if (isAvailable != null) {
                if (isAvailable) {
                    book.markAsReturned();
                } else {
                    book.markAsBorrowed();
                }
            }
       
            WriteBookToFile wbtf = new WriteBookToFile();
        wbtf.toFile(book);
        
       
        BookAiven bookAiven = new BookAiven();
        bookAiven.insertToAivenDb(book);
        
        redirectAttributes.addFlashAttribute("message", "Book added successfully!");
        return "redirect:/booklist";
        
    } catch (NumberFormatException e) {
        redirectAttributes.addFlashAttribute("error", "Quantity must be a number!");
        return "redirect:/add-book";
    } catch (IllegalArgumentException e) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/add-book";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        return "redirect:/add-book";
    }
}
   
    
    @PostMapping("/updateBook")
    public String updateBook(
            @RequestParam String bookID,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String quantity,
            RedirectAttributes redirectAttributes) {
        
        try {
         
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            if (quantity == null || Integer.parseInt(quantity) < 0) {
                throw new IllegalArgumentException("Quantity cannot be negative");
            }
            
 
            Book book = new Book(bookID, title, author);
            book.setQuantity(Integer.parseInt(quantity));
            
            
            BookAiven bookAiven = new BookAiven();
            bookAiven.updateBookInAivenDb(book);
            
            redirectAttributes.addFlashAttribute("success", "Book updated successfully!");
            return "redirect:/booklist";
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Quantity must be a number!");
            return "redirect:/edit-book/" + bookID;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/edit-book/" + bookID;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/edit-book/" + bookID;
        }
    }
    
    @PostMapping("/deleteBook/{bookID}")
    public String deleteBook(
            @PathVariable String bookID,
            RedirectAttributes redirectAttributes) {
        
        try {
           
            BookAiven bookAiven = new BookAiven();
            bookAiven.deleteBookFromAivenDb(bookID);
            
            redirectAttributes.addFlashAttribute("success", "Book deleted successfully!");
            return "redirect:/booklist";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/booklist";
        }
    }
    
    @GetMapping("/edit-book/{bookID}")
    public String editBookForm(@PathVariable String bookID, Model model) {
        try {
            BookAiven bookAiven = new BookAiven();
            Book book = bookAiven.getBookById(bookID);
            
            if (book == null) {
                throw new IllegalArgumentException("Book not found with ID: " + bookID);
            }
            
            model.addAttribute("book", book);
            return "edit-book";
        } catch (Exception e) {
            return "redirect:/booklist";
        }
    }
}