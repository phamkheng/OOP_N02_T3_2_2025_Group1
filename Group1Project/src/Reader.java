import java.util.ArrayList;
import java.util.List;

public class Reader {
    private String readerID;
    private String name;
    private String email;
    private String phone;
    private List<Loan> borrowedBooks;

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
}