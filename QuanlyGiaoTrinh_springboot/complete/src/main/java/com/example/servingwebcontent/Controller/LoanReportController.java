package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.servingwebcontent.Database.LoanStatisticsAiven;
import com.example.servingwebcontent.Database.LoanAiven;
import com.example.servingwebcontent.Database.ReaderAiven;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Reader;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reports")
public class LoanReportController {
    
 
    @GetMapping("/loan-detail/{id}")
    public String showLoanDetail(@PathVariable String id, Model model) {
        try {
            LoanAiven la = new LoanAiven();
            ArrayList<Loan> loans = la.loanAivenList();
            Loan loan = loans.stream()
                    .filter(l -> l.loanID.equals(id))
                    .findFirst()
                    .orElse(null);
            
            model.addAttribute("loan", loan);
            return "loan-detail";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/loans";
        }
    }
    

    @GetMapping("/search-loans")
    public String searchLoans(
        @RequestParam(required = false) String keyword,
        Model model) {
        
        try {
            LoanStatisticsAiven loanSearch = new LoanStatisticsAiven();
            ArrayList<Loan> loans = loanSearch.searchLoans(keyword);
            
            model.addAttribute("loans", loans);
            model.addAttribute("keyword", keyword);
            
            return "loan-search";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("loans", new ArrayList<Loan>());
            return "loan-search";
        }
    }
  
   
    @GetMapping("/overdue-loans")
    public String showOverdueLoans(Model model) {
        try {
            LoanStatisticsAiven loanStats = new LoanStatisticsAiven();
            ArrayList<Loan> overdueLoans = loanStats.getOverdueLoans();
            
          
            LocalDate today = LocalDate.now();
            Map<String, Integer> daysOverdueMap = new HashMap<>();
            
            for (Loan loan : overdueLoans) {
                LocalDate dueDate = LocalDate.parse(loan.dueDate);
                long daysOverdue = ChronoUnit.DAYS.between(dueDate, today);
                daysOverdueMap.put(loan.loanID, (int) daysOverdue);
            }
            
            model.addAttribute("overdueLoans", overdueLoans);
            model.addAttribute("daysOverdueMap", daysOverdueMap);
            
            return "loan-overdue";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("overdueLoans", new ArrayList<Loan>());
            return "loan-overdue";
        }
    }
    
    
    @GetMapping("/reader-history/{readerID}")
    public String showReaderHistory(@PathVariable String readerID, Model model) {
        try {
            ReaderAiven readerRepo = new ReaderAiven();
            Reader reader = readerRepo.getReaderById(readerID);
            
            if (reader == null) {
                return "redirect:/readers";
            }
            
            LoanStatisticsAiven loanStats = new LoanStatisticsAiven();
            ArrayList<Loan> loanHistory = loanStats.getReaderHistory(readerID);
            
            
            int totalLoans = loanHistory.size();
            int overdueCount = (int) loanHistory.stream()
                .filter(loan -> "Overdue".equals(loan.status))
                .count();
            
            model.addAttribute("reader", reader);
            model.addAttribute("loanHistory", loanHistory);
            model.addAttribute("totalLoans", totalLoans);
            model.addAttribute("overdueCount", overdueCount);
            
            return "reader-history";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/readers";
        }
    }
    

    @PostMapping("/return-book/{loanID}")
    public String returnBook(@PathVariable String loanID, RedirectAttributes redirectAttributes) {
        try {
            LoanAiven la = new LoanAiven();
            ArrayList<Loan> loans = la.loanAivenList();
            Loan loan = loans.stream()
                    .filter(l -> l.loanID.equals(loanID))
                    .findFirst()
                    .orElse(null);
            
            if (loan != null && "Borrowed".equals(loan.status)) {
                // Cập nhật trạng thái và ngày trả sách
                la.updateLoanStatus(loanID, LocalDate.now().toString());
                redirectAttributes.addFlashAttribute("successMessage", "Trả sách thành công!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Phiếu mượn không hợp lệ hoặc đã được trả!");
            }
            
            return "redirect:/loans";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi trả sách: " + e.getMessage());
            return "redirect:/loans";
        }
    }
}