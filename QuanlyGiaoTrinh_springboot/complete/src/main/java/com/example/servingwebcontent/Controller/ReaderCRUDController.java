package com.example.servingwebcontent.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.servingwebcontent.Database.ReaderOperationsAiven;
import com.example.servingwebcontent.Model.Reader;
@Controller
public class ReaderCRUDController {
    
   
    @GetMapping("/add-reader")
    public String addReaderPage() {
        return "add-reader";
    }
  
    @PostMapping("/add-reader")
    public String addReader(@RequestParam("readerID") String readerID,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("phone") String phone,
                           RedirectAttributes redirectAttributes) {
        try {
            Reader newReader = new Reader();
            newReader.readerID = readerID;
            newReader.name = name;
            newReader.email = email;
            newReader.phone = phone;
            
          
            ReaderOperationsAiven roa = new ReaderOperationsAiven();
            roa.insertToAivenDb(newReader);
            
            redirectAttributes.addFlashAttribute("successMessage", "Đã thêm độc giả thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm độc giả: " + e.getMessage());
        }
        
        return "redirect:/readerlist";
    }
    

    @GetMapping("/edit-reader")
    public String editReaderPage(@RequestParam("readerID") String readerID, Model model) {
        // Sử dụng ReaderOperationsAiven để lấy thông tin độc giả
        ReaderOperationsAiven roa = new ReaderOperationsAiven();
        Reader readerToEdit = roa.getReaderById(readerID);
        
        model.addAttribute("reader", readerToEdit);
        return "edit-reader";
    }
    
   
    @PostMapping("/edit-reader")
    public String editReader(@RequestParam("readerID") String readerID,
                            @RequestParam("name") String name,
                            @RequestParam("email") String email,
                            @RequestParam("phone") String phone,
                            RedirectAttributes redirectAttributes) {
        try {
            Reader updatedReader = new Reader();
            updatedReader.readerID = readerID;
            updatedReader.name = name;
            updatedReader.email = email;
            updatedReader.phone = phone;
            
           
            ReaderOperationsAiven roa = new ReaderOperationsAiven();
            roa.updateReaderInDb(updatedReader);
            
            redirectAttributes.addFlashAttribute("successMessage", "Đã cập nhật độc giả thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật độc giả: " + e.getMessage());
        }
        
        return "redirect:/readerlist";
    }
    
    
    @PostMapping("/delete-reader")
    public String deleteReader(@RequestParam("readerID") String readerID,
                              RedirectAttributes redirectAttributes) {
        try {
            // Sử dụng ReaderOperationsAiven thay vì ReaderDeleteAiven
            ReaderOperationsAiven roa = new ReaderOperationsAiven();
            roa.deleteReaderFromDb(readerID);
            
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa độc giả thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa độc giả: " + e.getMessage());
        }
        
        return "redirect:/readerlist";
    }
}