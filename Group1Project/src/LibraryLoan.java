import java.util.ArrayList;


public class LibraryLoan {

    ArrayList<Loan> listLoans = new ArrayList<>();

    public ArrayList<Loan> addLoan(Loan l) {
        try {
            listLoans.add(l);
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm giao dịch: " + e.getMessage());
        }
        return listLoans;
    }

    public ArrayList<Loan> deleteLoan(String loanId) {
        try {
            for (int i = 0; i < listLoans.size(); i++) {
                if (listLoans.get(i).loanID.equals(loanId)) {
                    listLoans.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa giao dịch: " + e.getMessage());
        }
        return listLoans;
    }

    public void readLoans() {
        try {
            for (Loan l : listLoans) {
                System.out.println("Loan ID: " + l.loanID);
                System.out.println("Book: " + l.book.getTitle());
                System.out.println("Reader: " + l.reader.name);
                System.out.println("Loan Date: " + l.borrowDate);
                System.out.println("Return Date: " + (l.returnDate != null ? l.returnDate : "Chưa trả"));
                System.out.println("Status: " + l.status);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc danh sách giao dịch: " + e.getMessage());
        }
    }

    public ArrayList<Loan> markAsReturned(String loanId, String returnDate) {
        try {
            for (Loan l : listLoans) {
                if (l.loanID.equals(loanId)) {
                    l.markReturned(returnDate);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật trạng thái trả sách: " + e.getMessage());
        }
        return listLoans;
    }

    public void checkOverdue(String currentDate) {
        try {
            for (Loan l : listLoans) {
                if (l.isOverdue(currentDate)) {
                    System.out.println("Loan ID " + l.loanID + " is overdue!");
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra quá hạn: " + e.getMessage());
        }
    }
}
