import java.util.Scanner;
public class Loan {
    String loanID;
    Book book;
    Reader reader;
    String borrowDate;
    String returnDate;
    String status = "Borrowed";

    public Loan(String loanID, Book book, Reader reader, String loanDate, String returnDate) {
        this.loanID = loanID;
        this.book = book;
        this.reader = reader;
        this.borrowDate = loanDate;
        this.returnDate = returnDate;
    }

    public void markReturned(String date) {
        this.returnDate = date;
        this.status = "Returned";
        book.markAsReturned();
    }

    public boolean isOverdue(String currentDate) {
    
        if (returnDate == null) return false;
        return currentDate.compareTo(returnDate) > 0;
    }
}