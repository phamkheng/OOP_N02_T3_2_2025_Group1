import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {

    ArrayList<Book> listBooks = new ArrayList<>();

    public ArrayList<Book> addBook(Book b) {
        listBooks.add(b);

        return listBooks;
    }

    public ArrayList<Book> deleteBook(String bookId) {

        for (int i = 0; i < listBooks.size(); i++) {
            if (listBooks.get(i).bookID.equals(bookId)) {
                listBooks.remove(i);
                break;
            }

        }
        return listBooks;
    }

    public void readBooks() {
        for (Book book : listBooks) {
            System.out.println("Book ID: " + book.bookID);
            System.out.println("Title: " + book.title);
            System.out.println("Author: " + book.author);
            System.out.println();
        }
    }

    public ArrayList<Book> editBook(String bookId) {

        for (int i = 0; i < listBooks.size(); i++) {
            if (listBooks.get(i).bookID.equals(bookId)) {
                // Assuming we want to edit the title and author

                Scanner title = new Scanner(System.in);

                System.out.print("Enter new title: ");
                String newTitle = title.nextLine();

                Scanner author = new Scanner(System.in);
                System.out.print("Enter new author: ");
                String newAuthor = author.nextLine();



                listBooks.get(i).title = newTitle; // Replace with actual new title
                listBooks.get(i).author = newAuthor; // Replace with actual new author
                break;
            }
        }

        return listBooks;
    }

}
