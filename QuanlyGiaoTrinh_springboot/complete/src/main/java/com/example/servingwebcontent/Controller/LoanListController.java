package com.example.servingwebcontent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.servingwebcontent.Database.LoanAiven;
import com.example.servingwebcontent.Model.Loan;

import java.util.ArrayList;

@Controller
public class LoanListController {
    
    @GetMapping("/loanlist")
    public String loanlist(Model model) {
        
        ArrayList<Loan> loanList = new ArrayList<Loan>();
        LoanAiven la = new LoanAiven();
        loanList = la.loanAivenList();
        model.addAttribute("ListOfLoans", loanList);
        
        // data to View is ${ListOfLoans}
        // return view is loanlist
        return "loanlist";
    }
} 