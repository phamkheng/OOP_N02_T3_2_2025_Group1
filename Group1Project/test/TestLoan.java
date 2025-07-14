

public class TestLoan {
    public static void main(String[] args) {
        Book book = new Book("OPP", "John Smith", 300);
        Reader reader = new Reader("R002", "Trần Quốc Việt Hùng", "c@example.com", "0987654321");

        Date loanDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(loanDate);
        cal.add(Calendar.DAY_OF_MONTH, 7); // ngày trả dự kiến là sau 7 ngày
        Date returnDate = cal.getTime();

        Loan loan = new Loan("L002", book, reader, loanDate, returnDate, "Borrowed");

        System.out.println("Loan ID: " + loan.getLoanID());
        System.out.println("Book title: " + loan.getBook().getTitle());
        System.out.println("Reader name: " + loan.getReader().getName());
        System.out.println("Status before return: " + loan.getStatus());

        loan.markReturned(new Date());

        System.out.println("Status after return: " + loan.getStatus());
    }
}
