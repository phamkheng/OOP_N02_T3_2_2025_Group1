import java.util.ArrayList;

public class LoanManager {
    private ArrayList<Loan> loanList = new ArrayList<>();
    private LibraryReader readerManager;
    private LibraryBook bookManager;

    public LoanManager(LibraryReader readerManager, LibraryBook bookManager) {
        this.readerManager = readerManager;
        this.bookManager = bookManager;
    }

    public boolean createLoan(String loanID, String readerID, String bookID, 
                            String borrowDate, String returnDate) {
        try {

            Reader reader = null;
            for (Reader r : readerManager.listReaders) {
                if (r.readerID.equals(readerID)) {
                    reader = r;
                    break;
                }
            }
            if (reader == null) {
                System.out.println("Reader không tồn tại!");
                return false;
            }

            Book book = null;
            for (Book b : bookManager.listBooks) {
                if (b.bookID.equals(bookID) && b.quantity > 0) {
                    book = b;
                    break;
                }
            }
            if (book == null) {
                System.out.println("Sách không tồn tại hoặc đã hết!");
                return false;
            }

            Loan newLoan = new Loan(loanID, book, reader, borrowDate, returnDate);
            loanList.add(newLoan);

            book.quantity--;

            System.out.println("Mượn sách thành công!");
            return true;

        } catch (Exception e) {
            System.out.println("Lỗi khi tạo phiếu mượn: " + e.getMessage());
            return false;
        }
    }
}