package com.example.servingwebcontent.Controller.LibraryControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.Database.ReaderSearchAiven;
import com.example.servingwebcontent.Model.Reader;

import java.util.ArrayList;

@Controller
public class ReaderSearchController {
    
    @GetMapping("/searchreader")
    public String searchReaderPage() {
        return "searchreader";
    }
    
    @PostMapping("/searchreader")
    public String searchReader(@RequestParam("keyword") String keyword, Model model) {
        ArrayList<Reader> searchResults = new ArrayList<Reader>();
        ReaderSearchAiven rsa = new ReaderSearchAiven();
        searchResults = rsa.readerAivenSearch(keyword);
        
        model.addAttribute("ListOfReaders", searchResults);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", searchResults.size());
        
        return "searchreader";
    }
}
