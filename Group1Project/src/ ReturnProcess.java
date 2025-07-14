import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReturnProcess {

    static class Loan {
        String loanID;
        Date returnDate;
        String status;

        Loan(String loanID, Date returnDate, String status) {
            this.loanID     = loanID;
            this.returnDate = returnDate;
            this.status     = status;
        }

        String getLoanID() { return loanID; }
        String getStatus() { return status; }
        void markReturned(Date date) {
            this.returnDate = date;
            this.status     = "Returned";
        }
    }

    public static void main(String[] args) {

        List<Loan> loans = new ArrayList<>();
        Date now = new Date();
        Date due = new Date(now.getTime() + 7L * 24 * 60 * 60 * 1000);
        loans.add(new Loan("L001", due, "Borrowed"));
        loans.add(new Loan("L002", due, "Returned"));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nhap loanID can tra (hoac 'exit' de thoat): ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Ket thuc chuong trinh.");
                break;
            }

            Loan found = null;
            for (Loan ln : loans) {
                if (ln.getLoanID().equalsIgnoreCase(input)) {
                    found = ln;
                    break;
                }
            }

            if (found == null || !found.getStatus().equals("Borrowed")) {
                System.out.println("Giao dich khong hop le");
                System.out.print("Muon thu lai tra sach? (yes/no): ");
                String retry = sc.nextLine().trim();
                if (retry.equalsIgnoreCase("yes")) {
                    continue;
                } else {
                    System.out.println("Ket thuc chuong trinh.");
                    break;
                }
            }

            found.markReturned(new Date());
            System.out.println("Tra sach thanh cong");
            break;
        }
        sc.close();
    }
}
