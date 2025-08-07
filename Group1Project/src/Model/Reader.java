package Model;

import Model.Loan;
import Model.Book;

public class Reader {
    public String readerID;
    public String name;
    public String email;
    public String phone;
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
    public void viewBorrowHistory() {
        System.out.println("Độc giả: " + name);
        System.out.println("ID: " + readerID);
        System.out.println("Email: " + email);
        System.out.println("SĐT: " + phone);
        if(borrowedCount == 0){
            System.out.println("Chưa muợn sách nào.");
        }
        else{
            System.out.println("Lịch sử mượn sách:");
            for (int i = 0; i < borrowedCount; i++) {
                System.out.println("ID sách: " + borrowedBooks[i].book.bookID);
                System.out.println("Tên sách: " + borrowedBooks[i].book.title);
                System.out.println("Tác giả: " + borrowedBooks[i].book.author);
            }
        }
    }
}