package com.example.servingwebcontent.Controller.LibraryControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.servingwebcontent.Database.ReaderInsertAiven;
import com.example.servingwebcontent.Database.ReaderUpdateAiven;
import com.example.servingwebcontent.Database.ReaderDeleteAiven;
import com.example.servingwebcontent.Database.ReaderAiven;
import com.example.servingwebcontent.Model.Reader;

import java.util.ArrayList;

@Controller
public class ReaderCRUDController {
    
    // Add new reader - GET form
    @GetMapping("/add-reader")
    public String addReaderPage() {
        return "add-reader";
    }
    
    // Add new reader - POST submit
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
            
            ReaderInsertAiven ria = new ReaderInsertAiven();
            ria.insertToAivenDb(newReader);
            
            redirectAttributes.addFlashAttribute("successMessage", "Đã thêm độc giả thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm độc giả: " + e.getMessage());
        }
        
        return "redirect:/readerlist";
    }
    
    // Edit reader - GET form
    @GetMapping("/edit-reader")
    public String editReaderPage(@RequestParam("readerID") String readerID, Model model) {
        // Get reader info to pre-fill form
        ReaderAiven ra = new ReaderAiven();
        ArrayList<Reader> readers = ra.readerAivenList();
        
        Reader readerToEdit = null;
        for (Reader r : readers) {
            if (r.readerID.equals(readerID)) {
                readerToEdit = r;
                break;
            }
        }
        
        model.addAttribute("reader", readerToEdit);
        return "edit-reader";
    }
    
    // Edit reader - POST submit
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
            
            ReaderUpdateAiven rua = new ReaderUpdateAiven();
            rua.updateReaderInDb(updatedReader);
            
            redirectAttributes.addFlashAttribute("successMessage", "Đã cập nhật độc giả thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật độc giả: " + e.getMessage());
        }
        
        return "redirect:/readerlist";
    }
    
    // Delete reader - POST
    @PostMapping("/delete-reader")
    public String deleteReader(@RequestParam("readerID") String readerID,
                              RedirectAttributes redirectAttributes) {
        try {
            ReaderDeleteAiven rda = new ReaderDeleteAiven();
            rda.deleteReaderFromDb(readerID);
            
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa độc giả thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa độc giả: " + e.getMessage());
        }
        
        return "redirect:/readerlist";
    }
}
