import java.util.ArrayList;
import java.util.Scanner;

public class LibraryBook {

    ArrayList<Book> listBooks = new ArrayList<>();

    public ArrayList<Book> addBook(Book b) {
        listBooks.add(b);
        return listBooks;
    }

    public ArrayList<Book> deleteBook(String bookId) {
        for (int i = 0; i < listBooks.size(); i++) {
            if (listBooks.get(i).getBookID().equals(bookId)) {
                listBooks.remove(i);
                break;
            }
        }
        return listBooks;
    }

    public void readBooks() {
        for (Book book : listBooks) {
            book.display();
            System.out.println();
        }
    }

    public ArrayList<Book> editBook(String bookId) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < listBooks.size(); i++) {
            if (listBooks.get(i).getBookID().equals(bookId)) {
                System.out.print("Enter new title: ");
                String newTitle = sc.nextLine();

                System.out.print("Enter new author: ");
                String newAuthor = sc.nextLine();

                listBooks.get(i).setTitle(newTitle);
                listBooks.get(i).setAuthor(newAuthor);
                break;
            }
        }
        return listBooks;
    }

    public void findBookByTitle(String keyword) {
        for (Book book : listBooks) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                book.display();
                System.out.println();
            }
        }
    }

    public void showAvailableBooks() {
        for (Book book : listBooks) {
            if (book.isAvailable()) {
                book.display();
                System.out.println();
            }
        }
    }
    public void borrowBook(String bookId) {
        for (Book book : listBooks) {
            if (book.getBookID().equals(bookId)) {
                if (book.isAvailable()) {
                    book.markAsBorrowed();
                    System.out.println("Đã mượn sách thành công.");
                } else {
                    System.out.println("Sách hiện không có sẵn.");
                }
                return;
            }
        }
        System.out.println("Không tìm thấy sách với ID đã nhập.");
    }

    
    public void returnBook(String bookId) {
        for (Book book : listBooks) {
            if (book.getBookID().equals(bookId)) {
                book.markAsReturned();
                System.out.println("Đã trả sách thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy sách với ID đã nhập.");
    }
}
