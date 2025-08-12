package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LibraryLoan libraryLoan;
    private final LoanManager loanManager;
    private final LibraryReader readerManager;
    private final LibraryBook bookManager;

    public LoanController() {
        this.libraryLoan = new LibraryLoan();
        this.readerManager = new LibraryReader();
        this.bookManager = new LibraryBook();
        this.loanManager = new LoanManager(readerManager, bookManager);
    }

    @GetMapping("")
    public String loanManagement(Model model) {
        model.addAttribute("loans", libraryLoan.listLoans);
        return "loan-management";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addLoan(
            @RequestParam String loanID,
            @RequestParam String readerID,
            @RequestParam String bookID,
            @RequestParam String borrowDate,
            @RequestParam String dueDate) {

        Map<String, Object> response = new HashMap<>();

        try {
            Reader reader = readerManager.listReaders.stream()
                    .filter(r -> r.readerID.equals(readerID))
                    .findFirst()
                    .orElse(null);

            if (reader == null) {
                response.put("Thành công", false);
                response.put("message", "Không tìm thấy độc giả với ID: " + readerID);
                return ResponseEntity.badRequest().body(response);
            }

            Book book = bookManager.listBooks.stream()
                    .filter(b -> b.bookID.equals(bookID) && b.quantity > 0)
                    .findFirst()
                    .orElse(null);

            if (book == null) {
                response.put("Thành công", false);
                response.put("message", "Sách không tồn tại hoặc đã hết với ID: " + bookID);
                return ResponseEntity.badRequest().body(response);
            }

            boolean loanExists = libraryLoan.listLoans.stream()
                    .anyMatch(l -> l.loanID.equals(loanID));

            if (loanExists) {
                response.put("Thành công", false);
                response.put("message", "ID phiếu mượn đã tồn tại: " + loanID);
                return ResponseEntity.badRequest().body(response);
            }

            Loan newLoan = new Loan(loanID, book, reader, borrowDate, dueDate);
            libraryLoan.addLoan(newLoan);
            book.quantity--;

            response.put("Thành công", true);
            response.put("message", "Thêm phiếu mượn thành công!");
            response.put("loan", createLoanResponse(newLoan));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("Thành công", false);
            response.put("message", "Lỗi khi thêm phiếu mượn: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete/{loanID}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteLoan(@PathVariable String loanID) {
        Map<String, Object> response = new HashMap<>();

        try {
            Loan loanToDelete = libraryLoan.listLoans.stream()
                    .filter(loan -> loan.loanID.equals(loanID))
                    .findFirst()
                    .orElse(null);

            if (loanToDelete == null) {
                response.put("Thành công", false);
                response.put("message", "Không tìm thấy phiếu mượn với ID: " + loanID);
                return ResponseEntity.notFound().build();
            }

            if (loanToDelete.returnDate == null && loanToDelete.book != null) {
                loanToDelete.book.quantity++;
            }

            libraryLoan.deleteLoan(loanID);

            response.put("Thành công", true);
            response.put("message", "Xóa phiếu mượn thành công!");
            response.put("deletedLoanID", loanID);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("Thành công", false);
            response.put("message", "Lỗi khi xóa phiếu mượn: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAllLoans() {
        Map<String, Object> response = new HashMap<>();

        try {
            ArrayList<Map<String, Object>> loanList = new ArrayList<>();
            for (Loan loan : libraryLoan.listLoans) {
                loanList.add(createLoanResponse(loan));
            }

            response.put("Thành công", true);
            response.put("loans", loanList);
            response.put("total", loanList.size());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("Thành công", false);
            response.put("message", "Lỗi khi lấy danh sách phiếu mượn: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/list")
    public String showLoanListPage(Model model) {
        model.addAttribute("loans", libraryLoan.listLoans);
        return "loan-list"; // loan-list.html trong templates
    }

    private Map<String, Object> createLoanResponse(Loan loan) {
        Map<String, Object> loanData = new HashMap<>();
        loanData.put("loanID", loan.loanID);
        loanData.put("bookTitle", loan.book != null ? loan.book.getTitle() : "N/A");
        loanData.put("bookID", loan.book != null ? loan.book.bookID : "N/A");
        loanData.put("readerName", loan.reader != null ? loan.reader.name : "N/A");
        loanData.put("readerID", loan.reader != null ? loan.reader.readerID : "N/A");
        loanData.put("borrowDate", loan.borrowDate);
        loanData.put("dueDate", loan.dueDate);
        loanData.put("returnDate", loan.returnDate);
        loanData.put("status", loan.status);
        return loanData;
    }
}
