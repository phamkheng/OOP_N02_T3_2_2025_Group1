package com.example.servingwebcontent.Controller.LibraryControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.servingwebcontent.Database.ReaderAiven;
import com.example.servingwebcontent.Model.Reader;

import java.util.ArrayList;

@Controller
public class ReaderListController {
    
    @GetMapping("/readerlist")
    public String readerlist(Model model) {
        
        ArrayList<Reader> readerList = new ArrayList<Reader>();
        ReaderAiven ra = new ReaderAiven();
        readerList = ra.readerAivenList();
        model.addAttribute("ListOfReaders", readerList);
       
        return "readerlist";
    }
} 