import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterReader {
     private ArrayList<Reader> readers;
    private Scanner scanner;

    public RegisterReader() {
        readers = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void registerNewReader() {
        boolean tryAgain;
        do {
            System.out.println("=== Đăng ký bạn đọc mới ===");
            System.out.print("Nhập readerID: ");
            String readerID = scanner.nextLine();

            System.out.print("Nhập tên: ");
            String name = scanner.nextLine();

            System.out.print("Nhập email: ");
            String email = scanner.nextLine();

            System.out.print("Nhập số điện thoại: ");
            String phone = scanner.nextLine();

            if (isValidInfo(readerID, name, email, phone)) {
                Reader newReader = new Reader(readerID, name, email, phone);
                readers.add(newReader);
                System.out.println(">>> Đăng ký thành công!\n");
                break;
            } else {
                System.out.println(">>> Thông tin không hợp lệ!");

                System.out.print("Bạn có muốn thử lại không? (có/không): ");
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
