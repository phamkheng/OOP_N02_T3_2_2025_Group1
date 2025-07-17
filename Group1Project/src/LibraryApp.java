import java.util.ArrayList;

public class LibraryApp {
    public static void main(String[] args) {
        Book b1 = new Book("B001", "OPP", "Viet Hung");
        Book b2 = new Book("B002", "Java", "Pham Khang");

        ArrayList<Book> listBooks = new ArrayList<>();
        listBooks.add(b1);
        listBooks.add(b2);

        System.out.println("Book 1 ID:    " + listBooks.get(0).bookID);
        System.out.println("Book 1 Title: " + listBooks.get(0).title);
        System.out.println("Book 1 Author:" + listBooks.get(0).author);
        System.out.println();
        System.out.println("Book 2 ID:    " + listBooks.get(1).bookID);
        System.out.println("Book 2 Title: " + listBooks.get(1).title);
        System.out.println("Book 2 Author:" + listBooks.get(1).author);
    }
}

