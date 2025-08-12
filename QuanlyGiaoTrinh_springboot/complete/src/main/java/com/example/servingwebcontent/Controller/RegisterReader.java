package com.example.servingwebcontent.Controller;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.example.servingwebcontent.Model.Reader;

public class RegisterReader {
    private ArrayList<Reader> readers;
    private Scanner scanner;

    
    public RegisterReader() {
        this(new Scanner(System.in));
    }

    public RegisterReader(Scanner scanner) {
        this.readers = new ArrayList<>();
        this.scanner = scanner;
    }

    public void registerNewReader() {
        boolean tryAgain = false;
        do {
            System.out.println("=== Đăng ký bạn đọc mới ===");
            System.out.print("Nhập readerID: ");
            String readerID = "";

            while (readerID.isEmpty()) {
                if (!scanner.hasNextLine()) {
                    System.out.println("Không còn input. Dừng đăng ký.");
                    return; // thoát an toàn nếu input đã hết
                }
                readerID = scanner.nextLine().trim();
            }

            System.out.print("Nhập tên: ");
            if (!scanner.hasNextLine()) { System.out.println("Không còn input."); return; }
            String name = scanner.nextLine().trim();

            System.out.print("Nhập email: ");
            if (!scanner.hasNextLine()) { System.out.println("Không còn input."); return; }
            String email = scanner.nextLine().trim();

            System.out.print("Nhập số điện thoại: ");
            if (!scanner.hasNextLine()) { System.out.println("Không còn input."); return; }
            String phone = scanner.nextLine().trim();

            if (isValidInfo(readerID, name, email, phone)) {
                Reader newReader = new Reader(readerID, name, email, phone);
                readers.add(newReader);
                System.out.println(">>> Đăng ký thành công!\n");
                break;
            } else {
                System.out.println(">>> Thông tin không hợp lệ!");
                System.out.print("Bạn có muốn thử lại không? (có/không): ");
                if (!scanner.hasNextLine()) { System.out.println("Không còn input."); return; }
                String answer = scanner.nextLine().trim().toLowerCase();
                tryAgain = answer.equals("có");
            }
        } while (tryAgain);
    }

    private boolean isValidInfo(String readerID, String name, String email, String phone) {
        return !readerID.isEmpty() &&
               !name.isEmpty() &&
               isValidEmail(email) &&
               isValidPhone(phone);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{9,11}");
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }
}