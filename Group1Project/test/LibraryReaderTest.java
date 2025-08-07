package test;

import Controller.LibraryReader;
import Model.Reader;

public class LibraryReaderTest {

    private LibraryReader libraryReader;

    public LibraryReaderTest() {
        libraryReader = new LibraryReader();
    }

    public void testAddReaders() {
        System.out.println("=== Test thêm độc giả ===");
        libraryReader.addReader(new Reader("R001", "Nguyen Van A", "a@example.com", "0909009000"));
        libraryReader.addReader(new Reader("R002", "Tran Thi B", "b@example.com", "0909009001"));
        libraryReader.addReader(new Reader("R003", "Le Van C", "c@example.com", "0909009002"));
        libraryReader.readReaders();
    }

    public void testEditReader() {
        System.out.println("=== Test sửa độc giả ===");
        boolean edited = libraryReader.editReader("R002", "Tran Thi B Updated", "bnew@example.com", "0988888888");
        if (edited) {
            System.out.println("Sửa thành công.");
        } else {
            System.out.println("Sửa thất bại, không tìm thấy ID.");
        }
        libraryReader.readReaders();
    }

    public void testDeleteReader() {
        System.out.println("=== Test xóa độc giả ===");
        boolean deleted = libraryReader.deleteReader("R001");
        if (deleted) {
            System.out.println("Xóa thành công.");
        } else {
            System.out.println("Xóa thất bại, không tìm thấy ID.");
        }
        libraryReader.readReaders();
    }

    // Bạn có thể thêm các hàm test khác ở đây
}
