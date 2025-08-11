package test;

import Controller.LibraryReader;
import Model.Reader;

public class TestLibraryReader {

    public static void runTest() {
        LibraryReader lib = new LibraryReader();

        // Thêm độc giả mẫu
        Reader r1 = new Reader("DG001", "Nguyen Van A", "a@example.com", "0123456789");
        Reader r2 = new Reader("DG002", "Tran Thi B", "b@example.com", "0987654321");
        Reader r3 = new Reader("DG003", "Le Van C", "c@example.com", "0111222333");

        lib.addReader(r1);
        lib.addReader(r2);
        lib.addReader(r3);

        // In danh sách ban đầu
        System.out.println("=== DANH SÁCH BAN ĐẦU ===");
        lib.readReaders();

        // Xóa 1 độc giả
        System.out.println("\n=== XÓA ĐỘC GIẢ CÓ ID DG002 ===");
        lib.deleteReader("DG002");

        // In danh sách sau khi xóa
        System.out.println("\n=== DANH SÁCH SAU KHI XÓA ===");
        lib.readReaders();

        // Thử xóa ID không tồn tại
        System.out.println("\n=== THỬ XÓA ID KHÔNG TỒN TẠI ===");
        lib.deleteReader("DG999");
    }
}
