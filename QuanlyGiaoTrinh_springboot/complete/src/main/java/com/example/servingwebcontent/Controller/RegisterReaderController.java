package com.example.servingwebcontent.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.servingwebcontent.Database.ReaderOperationsAiven;
import com.example.servingwebcontent.Model.Reader;
@Controller
public class RegisterReaderController {
    @GetMapping("/registerReader")
    public String registerReaderForm(Model model) {
        return "readerlist"; 
    }
    
    @PostMapping("/registerReader")
    public String registerReader(@RequestParam String readerID, 
                               @RequestParam String name, 
                               @RequestParam String email, 
                               @RequestParam String phone, 
                               Model model) {
        
        try {
            Reader reader = new Reader(readerID, name, email, phone);
            
          
            ReaderOperationsAiven roa = new ReaderOperationsAiven();
            roa.insertToAivenDb(reader);
            
            model.addAttribute("message", "Đăng ký độc giả thành công!");
            model.addAttribute("reader", reader);
            model.addAttribute("isSuccess", true);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi đăng ký: " + e.getMessage());
        }
        
        return "readerlist"; 
    }
}