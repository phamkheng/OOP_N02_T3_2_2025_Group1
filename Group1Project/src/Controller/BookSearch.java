package Controller;
import java.util.Scanner;
import Data.BookList;

public class BookSearch {
    private BookList bookList;
    private Scanner scanner;

    public BookSearch(BookList bookList) {
        this.bookList = bookList;
        scanner = new Scanner(System.in);
    }

    public void searchBook() {
        boolean continueSearch = true;

        while (continueSearch) {
            System.out.print("\nNhập tên sách hoặc tên tác giả cần tìm: ");
            String keyword = scanner.nextLine().trim();

            System.out.println("\nKết quả tìm kiếm:");
            bookList.searchByTitleOrAuthor(keyword);

            System.out.print("\nBạn có muốn tìm thêm sách khác không? (có/không): ");
            String again = scanner.nextLine().trim().toLowerCase();
            if (!again.equals("có")) {
                continueSearch = false;
            }
        }

        System.out.println("\nKết thúc tìm kiếm.");
    }
}
