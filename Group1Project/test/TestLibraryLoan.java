public class TestLibraryLoan {
    public static void test() {

        LibraryLoan libraryLoan = new LibraryLoan();
       Book b1 = new Book("B001", "OOP", "Viet Hung");
       Book b2 = new Book("B002", "Java", "Pham Khang");
       Book b3 = new Book("B003", "Python", "Nguyen Khoa");

        Reader r1 = new Reader("R001", "Hung", "a@gmail.com", "0123456789");
        Reader r2 = new Reader("R002", "Khang", "b@gmail.com", "0987654321");
        Reader r3 = new Reader("R003", "Khoa", "c@gmail.com", "0111222333");

        Loan l1 = new Loan("L001", b1, r1, "2025-07-15", "2025-07-20");
        Loan l2 = new Loan("L002", b2, r2, "2025-07-16", "2025-07-21");
        Loan l3 = new Loan("L003", b3, r3, "2025-07-17", "2025-07-22");

        libraryLoan.addLoan(l1);
        libraryLoan.addLoan(l2);
        libraryLoan.addLoan(l3);

        System.out.println("========== Danh sách sau khi thêm ==========");
        libraryLoan.readLoans();

        System.out.println("========== Xóa giao dịch L001 ==========");
        libraryLoan.deleteLoan("L001");
        libraryLoan.readLoans();

        System.out.println("========== Đánh dấu L003 đã trả ==========");
        libraryLoan.markAsReturned("L003", "2025-07-20");

        System.out.println("========== Danh sách sau khi trả L003 ==========");
        libraryLoan.readLoans();

        System.out.println("========== Kiểm tra quá hạn ngày 2025-07-25 ==========");
        libraryLoan.checkOverdue("2025-07-25");
    }
}
