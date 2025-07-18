public class Loan {
    String loanID;
    Book book;
    Reader reader;
    Date loanDate;
    Date returnDate;
    String status; 

    public Loan() {}

    public Loan(String loanID, Book book, Reader reader, Date loanDate) {
        this.loanID = loanID;
        this.book = book;
        this.reader = reader;
        this.loanDate = loanDate;
        this.status = "Borrowed";
    }

    public void markReturned(Date date) {
        this.returnDate = date;
        this.status = "Returned";
        this.book.markAsReturned();
        this.reader.returnBook(this);
    }

    public boolean isOverdue(Date currentDate) {
        if (returnDate == null) return false;
        return currentDate.after(returnDate);
    }
}