public class TestLibraryReader {
    public static void test() {

        LibraryReader libraryReader = new LibraryReader();
        Reader r1 = new Reader("R001", "Viet Hung", "a@gmail.com", "0123456789");
        Reader r2 = new Reader("R002", "Pham Khang", "b@gmail.com", "0987654321");
        Reader r3 = new Reader("R003", "Nguyen Khoa", "c@gmail.com", "0111222333");

        libraryReader.addReader(r1);
        libraryReader.addReader(r2);
        libraryReader.addReader(r3);

        System.out.println("========== Danh sách sau khi thêm ==========");
        libraryReader.readReaders();

        System.out.println("========== Xóa bạn đọc R001 ==========");
        libraryReader.deleteReader("R001");
        libraryReader.readReaders();

        System.out.println("========== Chỉnh sửa bạn đọc R003 ==========");
        libraryReader.editReader("R003");

        System.out.println("========== Danh sách sau khi chỉnh sửa ==========");
        libraryReader.readReaders();
    }

    public static void main(String[] args) {
        test();
    }
}
