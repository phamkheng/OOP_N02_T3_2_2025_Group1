// TestBook.java
public class TestBook {
    public static void testBook() {
        Book b = new Book("B001", "OPP", "Viet Hung");
        System.out.println("== Test Book ==");
        System.out.println("ID:     " + b.bookID);
        System.out.println("Title:  " + b.title);
        System.out.println("Author: " + b.author);
    }
}
