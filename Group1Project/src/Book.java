public class Book{
    private String bookID;
    private String title;
    private String author;
    private boolean available; // Sử dụng kiểu nguyên thủy

    // constructor không tham số
    public Book() {
        this.title = "";
        this.author = "";
        this.bookID = "";
        this.available = true;
    }
    // constructor có tham số
    public Book(String bookID, String title, String author, int numPages, boolean available) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.available = available;
    }
    public String getBookID() {
        return bookID;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }
    // kiểm tra trạng thái của sách
    public String checkAvailability() {
        return available ? "có sẵn" : "trống";
    }

    public void markAsBorrowed() {
        available = false;
    }

    public void markAsReturned() {
        available = true;
    }
    // in ra màn hình thông số của sách
    public void display() {
        System.out.println("Tên Sách: " + title);
        System.out.println("Mã: " + bookID);
        System.out.println("Tác giả: " + author);
        System.out.println("Trạng thái: " + checkAvailability());
    }
}