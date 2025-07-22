import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> books;

    public BookList() {
        books = new ArrayList<>();
        mockData(); // Có thể xoá nếu không cần dữ liệu mẫu
    }

    private void mockData() {
        books.add(new Book("B001", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài"));
        books.add(new Book("B002", "Tôi Thấy Hoa Vàng Trên Cỏ Xanh", "Nguyễn Nhật Ánh"));
        books.add(new Book("B003", "Harry Potter", "J.K. Rowling"));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Danh sách sách trống.");
        } else {
            for (Book book : books) {
                book.display();
            }
        }
    }

    public void searchByTitleOrAuthor(String keyword) {
        boolean found = false;
        keyword = keyword.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword) ||
                book.getAuthor().toLowerCase().contains(keyword)) {
                book.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sách phù hợp.");
        }
    }

    // Tuỳ chọn: tìm sách theo ID (dùng khi mượn sách)
    public Book findByID(String id) {
        for (Book book : books) {
            if (book.getBookID().equalsIgnoreCase(id)) {
                return book;
            }
        }
        return null;
    }
}