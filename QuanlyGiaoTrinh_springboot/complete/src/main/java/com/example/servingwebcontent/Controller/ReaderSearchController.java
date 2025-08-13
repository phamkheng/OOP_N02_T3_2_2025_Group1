package com.example.servingwebcontent.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.servingwebcontent.Database.ReaderOperationsAiven;
import com.example.servingwebcontent.Model.Reader;
import java.util.ArrayList;
@Controller
public class ReaderSearchController {
    
    @GetMapping("/searchreader")
    public String searchReaderPage() {
        return "readerlist"; 
    }
    
    @PostMapping("/searchreader")
    public String searchReader(@RequestParam("keyword") String keyword, Model model) {
        ArrayList<Reader> searchResults = new ArrayList<Reader>();
       
        ReaderOperationsAiven roa = new ReaderOperationsAiven();
        searchResults = roa.readerAivenSearch(keyword);
        
        model.addAttribute("ListOfReaders", searchResults);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", searchResults.size());
        model.addAttribute("isSearch", true);
        
        return "readerlist"; 
    }
}