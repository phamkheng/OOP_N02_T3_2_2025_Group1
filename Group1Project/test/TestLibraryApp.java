public class TestLibraryApp {
    public static void test() {

        LibraryApp libraryApp = new LibraryApp();
        Book b1 = new Book("B001", "OPP", "Viet Hung");

        Book b2 = new Book("B002", "Java", "Viet Hung");

        libraryApp.addBook(b2);
        libraryApp.addBook(b1);

        System.out.println("==================danh sach sau khi them book: ===========================");

        libraryApp.readBooks();

        System.out.println("=====================danh sach sau khi xoa book B001: ====================");

        libraryApp.deleteBook("B001");

        libraryApp.readBooks();

        System.out.println("=====================danh sach sau khi edit B001: ====================");
        libraryApp.editBook("B002");

        System.out.println("Danh sach hien tai ");

        libraryApp.readBooks();
    }
}
