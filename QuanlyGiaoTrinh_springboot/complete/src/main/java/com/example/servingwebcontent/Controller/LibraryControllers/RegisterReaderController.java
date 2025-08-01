package com.example.servingwebcontent.Controller.LibraryControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servingwebcontent.Model.Reader;

@Controller
public class RegisterReaderController {

    @GetMapping("/registerReader")
    public String registerReaderForm(Model model) {
        return "registerReader";
    }

    @PostMapping("/registerReader")
    public String registerReader(@RequestParam String readerID, 
                               @RequestParam String name, 
                               @RequestParam String email, 
                               @RequestParam String phone, 
                               Model model) {
        
        try {
            Reader reader = new Reader(readerID, name, email, phone);
            
           
            
            model.addAttribute("message", "Đăng ký độc giả thành công!");
            model.addAttribute("reader", reader);
            
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi đăng ký: " + e.getMessage());
        }
        
        return "registerReader";
    }
} 