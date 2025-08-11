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

    // Xóa độc giả theo ID
    public boolean deleteReader(String readerId) {
        for (int i = 0; i < listReaders.size(); i++) {
            if (listReaders.get(i).readerID.equals(readerId)) {
                listReaders.remove(i);
                System.out.println("Xóa thành công độc giả có ID: " + readerId);
                return true;
            }
        }
        System.out.println("Không tìm thấy độc giả với ID: " + readerId);
        return false;
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

    // Menu test chức năng
    public static void main(String[] args) {
        LibraryReader lib = new LibraryReader();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ ĐỘC GIẢ =====");
            System.out.println("1. Nhập danh sách độc giả");
            System.out.println("2. Xem danh sách độc giả");
            System.out.println("3. Xóa độc giả theo ID");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    lib.inputReaders();
                    break;
                case 2:
                    lib.readReaders();
                    break;
                case 3:
                    System.out.print("Nhập ID độc giả cần xóa: ");
                    String id = sc.nextLine();
                    lib.deleteReader(id);
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
