package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.servingwebcontent.Database.LoanAiven;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Reader;
import java.util.ArrayList;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @GetMapping("")
    public String listLoans(Model model) {
        try {
            System.out.println("=== LOADING LOANS LIST ===");
            LoanAiven la = new LoanAiven();
            ArrayList<Loan> loans = la.loanAivenList();
            System.out.println("Loaded " + loans.size() + " loans from database");
            
            for (int i = 0; i < loans.size(); i++) {
                Loan loan = loans.get(i);
                System.out.println("Loan " + i + ": " + loan.loanID + " - " + 
                                 (loan.book != null ? loan.book.title : "null book") + " - " + 
                                 (loan.reader != null ? loan.reader.name : "null reader"));
            }
            
            model.addAttribute("ListOfLoans", loans);
            return "loanlist";
        } catch (Exception e) {
            System.out.println("ERROR loading loans: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("ListOfLoans", new ArrayList<Loan>());
            return "loanlist";
        }
    }

    @GetMapping("/add")
    public String addLoanForm(Model model) {
        model.addAttribute("loan", new Loan("", new Book("", "", ""), new Reader("", "", "", ""), "", ""));
        return "loan-form";
    }

    @PostMapping("/save")
    public String saveLoan(@RequestParam String loanID,
                          @RequestParam String bookID,
                          @RequestParam String bookTitle,
                          @RequestParam String readerID,
                          @RequestParam String readerName,
                          @RequestParam String borrowDate,
                          @RequestParam String dueDate,
                          RedirectAttributes redirectAttributes) {
        try {
            System.out.println("=== SAVING LOAN ===");
            System.out.println("Loan ID: " + loanID);
            System.out.println("Book: " + bookID + " - " + bookTitle);
            System.out.println("Reader: " + readerID + " - " + readerName);
            
            Book book = new Book(bookID, bookTitle, "Tác giả");
            Reader reader = new Reader(readerID, readerName, "", "");
            Loan loan = new Loan(loanID, book, reader, borrowDate, dueDate);
            loan.status = "Borrowed";
            
            LoanAiven la = new LoanAiven();
            la.insertLoan(loan);
            
            System.out.println("Loan saved to database successfully!");
            redirectAttributes.addFlashAttribute("successMessage", "Thêm thành công: " + loanID);
        } catch (Exception e) {
            System.out.println("ERROR saving loan: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/loans";
    }

    @GetMapping("/edit/{id}")
    public String editLoanForm(@PathVariable String id, Model model) {
        try {
            LoanAiven la = new LoanAiven();
            ArrayList<Loan> loans = la.loanAivenList();
            Loan loanToEdit = loans.stream()
                    .filter(l -> l.loanID.equals(id))
                    .findFirst()
                    .orElse(null);
            model.addAttribute("loan", loanToEdit);
            return "loan-form";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/loans";
        }
    }

    @PostMapping("/update")
    public String updateLoan(@RequestParam String loanID,
                           @RequestParam String bookID,
                           @RequestParam String bookTitle,
                           @RequestParam String readerID,
                           @RequestParam String readerName,
                           @RequestParam String borrowDate,
                           @RequestParam String dueDate,
                           @RequestParam(required = false) String returnDate,
                           @RequestParam(required = false) String status,
                           RedirectAttributes redirectAttributes) {
        try {
            Book book = new Book(bookID, bookTitle, "Tác giả");
            Reader reader = new Reader(readerID, readerName, "", "");
            Loan loan = new Loan(loanID, book, reader, borrowDate, dueDate);
            
            if (returnDate != null && !returnDate.isEmpty()) {
                loan.returnDate = returnDate;
            }
            if (status != null && !status.isEmpty()) {
                loan.status = status;
            } else {
                loan.status = "Borrowed";
            }
            
            LoanAiven la = new LoanAiven();
            la.updateLoan(loan);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/loans";
    }

    @GetMapping("/delete/{id}")
    public String deleteLoan(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            LoanAiven la = new LoanAiven();
            la.deleteLoan(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/loans";
    }
}


