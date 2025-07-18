public class Reader {
    String readerID;
    String name;
    String email;
    String phone;
    Loan[] borrowedBooks = new Loan[100];
    int borrowedCount = 0;

    public Reader() {}

    public Reader(String readerID, String name, String email, String phone) {
        this.readerID = readerID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void borrowBook(Book book, Loan loan) {
        borrowedBooks[borrowedCount++] = loan;
        book.markAsBorrowed();
    }

    public void returnBook(Loan loan) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedBooks[i] == loan) {
                
                for (int j = i; j < borrowedCount - 1; j++) {
                    borrowedBooks[j] = borrowedBooks[j + 1];
                }
                borrowedBooks[--borrowedCount] = null;
                break;
            }
        }
    }
}