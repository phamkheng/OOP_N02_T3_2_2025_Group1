package com.example.servingwebcontent.service;

import java.util.ArrayList;
import com.example.servingwebcontent.Model.Book;
import com.example.servingwebcontent.Model.Loan;
import com.example.servingwebcontent.Model.Reader;
import com.example.servingwebcontent.Database.LoanAiven;

public class LoanManager {
    private ArrayList<Loan> loanList = new ArrayList<>();
    private LibraryReader readerManager;
    private LibraryBook bookManager;
    private LoanAiven loanAiven;

    public LoanManager(LibraryReader readerManager, LibraryBook bookManager) {
        this.readerManager = readerManager;
        this.bookManager = bookManager;
        this.loanAiven = new LoanAiven();
    }

    public boolean createLoan(String loanID, String readerID, String bookID, String borrowDate, String dueDate) {
        try {
            for (Loan l : loanList) {
                if (l.loanID.equals(loanID)) {
                    return false;
                }
            }
            Reader reader = readerManager.listReaders.stream()
                    .filter(r -> r.readerID.equals(readerID))
                    .findFirst().orElse(null);
            if (reader == null) {
                return false;
            }
            Book book = bookManager.listBooks.stream()
                    .filter(b -> b.bookID.equals(bookID) && b.quantity > 0)
                    .findFirst().orElse(null);
            if (book == null) {
                return false;
            }
            Loan newLoan = new Loan(loanID, book, reader, borrowDate, dueDate);
            loanList.add(newLoan);
            book.quantity--;
            loanAiven.insertLoan(newLoan);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteLoan(String loanID) {
        try {
            Loan loan = loanList.stream()
                    .filter(l -> l.loanID.equals(loanID))
                    .findFirst().orElse(null);
            if (loan == null) {
                return false;
            }
            if (loan.returnDate == null && loan.book != null) {
                loan.book.quantity++;
            }
            loanList.remove(loan);
            loanAiven.deleteLoan(loanID);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Loan> getAllLoans() {
        return loanList;
    }

    public boolean updateLoan(Loan updatedLoan) {
        try {
            for (int i = 0; i < loanList.size(); i++) {
                if (loanList.get(i).loanID.equals(updatedLoan.loanID)) {
                    loanList.set(i, updatedLoan);
                    loanAiven.updateLoan(updatedLoan);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
                        System.out.println("Lỗi khi tạo phiếu mượn: " + e.getMessage());
            return false;
        }
    }

    public Loan getLoanById(String loanID) {
        return loanList.stream()
                .filter(l -> l.loanID.equals(loanID))
                .findFirst().orElse(null);
    }
}
