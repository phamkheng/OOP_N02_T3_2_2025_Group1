package Model;
public class Loan {
    public String loanID;
    public Book book;
    public Reader reader;
    public String borrowDate;
    public String returnDate;
    public String dueDate; 
    public String status = "Borrowed";

    public Loan(String loanID, Book book, Reader reader, String borrowDate, String dueDate) {
        this.loanID = loanID;
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    public void markReturned(String date) {
        this.returnDate = date;
        this.status = "Returned";
        if (book != null) {
            book.markAsReturned();
        }
    }

    public boolean isOverdue(String currentDate) {
       
        if (returnDate == null && dueDate != null) {
            return currentDate.compareTo(dueDate) > 0;
        }
        return false;
    }
}