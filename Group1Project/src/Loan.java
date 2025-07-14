import java.util.Date;

public class Loan {
    private String loanID;
    private Book book;
    private Reader reader;
    private Date loanDate;
    private Date returnDate;
    private String status;

    public Loan() {}
    public Loan(String loanID, Book book, Reader reader, Date loanDate, Date returnDate, String status) {
        this.loanID = loanID;
        this.book = book;
        this.reader = reader;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public void markReturned(Date returnDate) {
        this.returnDate = returnDate;
        this.status = "Returned";
    }

    public boolean isOverdue(Date currentDate) {
        return currentDate.after(returnDate);
    }
}
