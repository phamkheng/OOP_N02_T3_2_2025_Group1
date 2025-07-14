public class Loan {
    String loanID;
    Book book;
    Reader reader;
    Date loanDate;
    Date returnDate;
    String status;

 

    public void markReturned(Date date) {
        this.returnDate = date;
        this.status = "Returned";
    }

    public boolean isOverdue(Date currentDate) {
        // kiểm tra quá hạn
        return currentDate.after(returnDate);
    }
    public static Loan findLoan(List<Loan> loans, String id) {
        for (Loan ln : loans) {
            if (ln.getLoanID().equals(id) || ln.getBook().getTitle().equals(id)) {
                return ln;
            }
        }
        return null;
    }
}
