

public class Main {
 
    private static List<Book> books   = new ArrayList<>();
    private static List<Reader> readers = new ArrayList<>();
    private static List<Loan> loans   = new ArrayList<>();

    public static void main(String[] args) {
    

        returnBookProcess();
    }

    private static void returnBookProcess() {
        Scanner sc = new Scanner(System.in);
        boolean retry;

        do {
            System.out.println("Nhập loanID hoặc BookID cần trả:");
            String id = sc.nextLine().trim();

            // Activity 2: Tìm Loan tương ứng
            Loan loan = Loan.findLoan(loans, id);

            if (loan == null || !"Borrowed".equals(loan.getStatus())) {
                // Activity 5: Giao dịch không hợp lệ
                System.out.println("Giao dịch không hợp lệ");
                System.out.println("Muốn thử lại trả sách? (yes/no)");
                String ans = sc.nextLine().trim().toLowerCase();
                retry = ans.equals("yes");
                continue;
            }

            // Activity 3: Đánh dấu đã trả
            loan.markReturned(new Date());

            // Activity 6: Cập nhật trạng thái sách
            Book book = loan.getBook();
            book.markAsReturned();

            // Activity 7: Xóa giao dịch khỏi borrowedBooks của bạn đọc
            Reader reader = loan.getReader();
            reader.getBorrowedBooks().remove(loan);

            // Activity 4: Thông báo thành công
            System.out.println("Trả sách thành công");
            retry = false;
        } while (retry);

        sc.close();
    }
}
