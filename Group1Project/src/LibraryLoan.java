import java.util.ArrayList;
import java.util.Scanner;

public class LibraryLoan {

    ArrayList<Loan> listLoans = new ArrayList<>();

    public ArrayList<Loan> addLoan(Loan l) {
        listLoans.add(l);
        return listLoans;
    }

    public ArrayList<Loan> deleteLoan(String loanId) {
        
        for (int i = 0; i < listLoans.size(); i++) {
            if (listLoans.get(i).loanID.equals(loanId)) {
                listLoans.remove(i);
                break;
            }
        }
        return listLoans;
    }

    public void readLoans() {
        for (Loan l : listLoans) {
            System.out.println("Loan ID: " + l.loanID);
            System.out.println("Book: " + l.book.getTitle());
            System.out.println("Reader: " + l.reader.name);
            System.out.println("Loan Date: " + l.loanDate);
            System.out.println("Return Date: " + (l.returnDate != null ? l.returnDate : "Chưa trả"));
            System.out.println("Status: " + l.status);
            System.out.println();
        }
    }

    public ArrayList<Loan> markAsReturned(String loanId, String returnDate) {
        for (Loan l : listLoans) {
            if (l.loanID.equals(loanId)) {
                l.markReturned(returnDate);
                break;
            }
        }
        return listLoans;
    }

    public void checkOverdue(String currentDate) {
        for (Loan l : listLoans) {
            if (l.isOverdue(currentDate)) {
                System.out.println("Loan ID " + l.loanID + " is overdue!");
            }
        }
    }
}