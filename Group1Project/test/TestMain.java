
public class TestMain {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        List<Reader> readers = new ArrayList<>();
        List<Loan> loans = new ArrayList<>();

        
        Book book = new Book("Java cơ bản", "Nguyễn Văn A", 250);
        Reader reader = new Reader("R004", "Ngô Văn E", "e@example.com", "0345678910");
        Loan loan = new Loan("L005", book, reader, new Date(), null, "Borrowed");

        books.add(book);
        readers.add(reader);
        loans.add(loan);
        reader.getBorrowedBooks().add(loan);


        System.out.println("--- Trạng thái ban đầu ---");
        System.out.println("Sách: " + book.getTitle() + ", Trạng thái: " + book.checkAvailability());
        System.out.println("Bạn đọc: " + reader.getName());
        System.out.println("Số sách đang mượn: " + reader.getBorrowedBooks().size());

     
        if ("Borrowed".equals(loan.getStatus())) {
            loan.markReturned(new Date());
            book.markAsReturned();
            reader.getBorrowedBooks().remove(loan);
            System.out.println("\nTrả sách thành công!");
        }

        // In trạng thái sau khi trả
        System.out.println("\n--- Sau khi trả sách ---");
        System.out.println("Sách: " + book.getTitle() + ", Trạng thái: " + book.checkAvailability());
        System.out.println("Số sách đang mượn: " + reader.getBorrowedBooks().size());
    }
}