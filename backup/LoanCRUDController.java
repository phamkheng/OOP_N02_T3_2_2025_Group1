// package com.example.servingwebcontent.Controller.LibraryControllers;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// import com.example.servingwebcontent.Database.LoanAiven;
// import com.example.servingwebcontent.Model.Book;
// import com.example.servingwebcontent.Model.Loan;
// import com.example.servingwebcontent.Model.Reader;
// import java.util.ArrayList;

// @Controller
// public class LoanCRUDController {

//     @GetMapping("/loans")
//     public String listLoans(Model model) {
//         LoanAiven la = new LoanAiven();
//         ArrayList<Loan> loans = la.loanAivenList();
//         model.addAttribute("ListOfLoans", loans);
//         return "loanlist";
//     }

//     @GetMapping("/loans/add")
//     public String addLoanForm(Model model) {
//         model.addAttribute("loan", new Loan("", new Book("", "", ""), new Reader("", "", "", ""), "", ""));
//         return "loan-form";
//     }

//     @PostMapping("/loans/save")
//     public String saveLoan(@RequestParam String loanID,
//                           @RequestParam String bookID,
//                           @RequestParam String bookTitle,
//                           @RequestParam String readerID,
//                           @RequestParam String readerName,
//                           @RequestParam String borrowDate,
//                           @RequestParam String dueDate,
//                           RedirectAttributes redirectAttributes) {
//         try {
//             Book book = new Book(bookID, bookTitle, "Tác giả");
//             Reader reader = new Reader(readerID, readerName, "", "");
//             Loan loan = new Loan(loanID, book, reader, borrowDate, dueDate);
            
//             LoanAiven la = new LoanAiven();
//             la.insertLoan(loan);
//             redirectAttributes.addFlashAttribute("successMessage", "Thêm thành công");
//         } catch (Exception e) {
//             redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
//         }
//         return "redirect:/loans";
//     }

//     @GetMapping("/loans/edit/{id}")
//     public String editLoanForm(@PathVariable String id, Model model) {
//         LoanAiven la = new LoanAiven();
//         ArrayList<Loan> loans = la.loanAivenList();
//         Loan loanToEdit = loans.stream()
//                 .filter(l -> l.loanID.equals(id))
//                 .findFirst()
//                 .orElse(null);
//         model.addAttribute("loan", loanToEdit);
//         return "loan-form";
//     }

//     @PostMapping("/loans/update")
//     public String updateLoan(@RequestParam String loanID,
//                            @RequestParam String bookID,
//                            @RequestParam String bookTitle,
//                            @RequestParam String readerID,
//                            @RequestParam String readerName,
//                            @RequestParam String borrowDate,
//                            @RequestParam String dueDate,
//                            @RequestParam(required = false) String returnDate,
//                            @RequestParam(required = false) String status,
//                            RedirectAttributes redirectAttributes) {
//         try {
//             Book book = new Book(bookID, bookTitle, "Tác giả");
//             Reader reader = new Reader(readerID, readerName, "", "");
//             Loan loan = new Loan(loanID, book, reader, borrowDate, dueDate);
            
//             if (returnDate != null && !returnDate.isEmpty()) {
//                 loan.returnDate = returnDate;
//             }
//             if (status != null && !status.isEmpty()) {
//                 loan.status = status;
//             }
            
//             LoanAiven la = new LoanAiven();
//             la.updateLoan(loan);
//             redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công");
//         } catch (Exception e) {
//             redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
//         }
//         return "redirect:/loans";
//     }

//     @GetMapping("/loans/delete/{id}")
//     public String deleteLoan(@PathVariable String id, RedirectAttributes redirectAttributes) {
//         try {
//             LoanAiven la = new LoanAiven();
//             la.deleteLoan(id);
//             redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công");
//         } catch (Exception e) {
//             redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
//         }
//         return "redirect:/loans";
//     }
// }