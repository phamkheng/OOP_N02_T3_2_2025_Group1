package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import Model.Reader;

public class LibraryReader {

    private ArrayList<Reader> listReaders = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Nhập n độc giả
    public void inputReaders() {
        System.out.print("Nhập số lượng độc giả cần thêm: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin độc giả thứ " + (i + 1) + ":");

            System.out.print("Reader ID: ");
            String id = sc.nextLine();

            System.out.print("Tên: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("SĐT: ");
            String phone = sc.nextLine();

            Reader r = new Reader(id, name, email, phone);

            if (addReader(r)) {
                System.out.println("Thêm độc giả thành công.\n");
            } else {
                System.out.println("Thêm độc giả thất bại.\n");
            }
        }
    }

    // Thêm độc giả, kiểm tra trùng ID
    public boolean addReader(Reader r) {
        if (r == null || r.readerID == null || r.readerID.isEmpty()) {
            System.out.println("Thông tin độc giả không hợp lệ.");
            return false;
        }

        for (Reader existing : listReaders) {
            if (existing.readerID.equals(r.readerID)) {
                System.out.println("Độc giả với ID này đã tồn tại.");
                return false;
            }
        }

        listReaders.add(r);
        return true;
    }

   // Hàm xóa độc giả theo tên hoặc mã
    public void deleteReaderFlexible() {
        if (listReaders.isEmpty()) {
            System.out.println("Danh sách độc giả hiện đang trống.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã hoặc tên độc giả cần xóa: ");
        String input = sc.nextLine().trim();

        boolean removed = listReaders.removeIf(reader ->
            reader.readerID.equalsIgnoreCase(input) ||
            reader.name.equalsIgnoreCase(input)
        );

        if (removed) {
        System.out.println("Đã xóa độc giả có mã hoặc tên khớp với: " + input);
        } else {
        System.out.println("Không tìm thấy độc giả có mã hoặc tên: " + input);
        }
    }


    // In danh sách độc giả
    public void readReaders() {
        if (listReaders.isEmpty()) {
            System.out.println("Không có độc giả nào trong hệ thống.");
            return;
        }

        System.out.println("Danh sách độc giả hiện tại:");
        for (Reader r : listReaders) {
            System.out.println("Reader ID: " + r.readerID);
            System.out.println("Name: " + r.name);
            System.out.println("Email: " + r.email);
            System.out.println("Phone: " + r.phone);
            System.out.println("-----------------------");
        }
    }

    // Tìm kiếm theo tên
    public Reader findReaderByName(String name) {
        for (Reader r : listReaders) {
            if (r.name.equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }

    // Tìm kiếm theo ID
    public Reader findReaderById(String id) {
        for (Reader r : listReaders) {
            if (r.readerID.equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }

    // Hiển thị kết quả tìm kiếm
    public void searchReaderByName() {
        System.out.print("Nhập tên độc giả cần tìm: ");
        String name = sc.nextLine();
        Reader result = findReaderByName(name);

        if (result != null) {
            System.out.println("Đã tìm thấy độc giả:");
            System.out.println("Reader ID: " + result.readerID);
            System.out.println("Name: " + result.name);
            System.out.println("Email: " + result.email);
            System.out.println("Phone: " + result.phone);
        } else {
            System.out.println("Không tìm thấy độc giả có tên: " + name);
        }
    }
}
