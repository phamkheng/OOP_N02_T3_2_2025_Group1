import java.util.ArrayList;
import java.util.List;

public class Reader {
    private String readerID;
    private String name;
    private String email;
    private String phone;
    private List<Loan> borrowedBooks;
}
    public Reader() {
        this.borrowedBooks = new ArrayList<>();
    }

    public Reader(String readerID, String name, String email, String phone) {
        this.readerID = readerID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        // Ví dụ tạo loan mới và thêm vào danh sách
        String loanID = "L" + (borrowedBooks.size() + 1);
        java.util.Date today = new java.util.Date();
        java.util.Date returnDate = new java.util.Date(today.getTime() + 14L * 24 * 60 * 60 * 1000); // +14 ngày

        Loan newLoan = new Loan(loanID, book, this, today, returnDate, "Borrowed");
        borrowedBooks.add(newLoan);

        // Đánh dấu sách đã mượn
        book.markAsBorrowed();

        System.out.println("Mượn thành công: " + book.getTitle());
    }

    public void returnBook(Book book) {
        for (Loan loan : borrowedBooks) {
            if (loan.getBook().equals(book) && loan.getStatus().equals("Borrowed")) {
                loan.markReturned(new java.util.Date());
                book.markAsReturned();
                System.out.println("Đã trả sách: " + book.getTitle());
                return;
            }
        }
        System.out.println("Không tìm thấy sách đang mượn để trả.");
    }

    public List<Loan> viewBorrowHistory() {
        return borrowedBooks;
    }