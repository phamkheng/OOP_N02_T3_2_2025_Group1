public class Book{
    private String bookID;
    private String title;
    private String author;
    private boolean available;

  
    public Book() {
        this.title = "";
        this.author = "";
        this.bookID = "";
        this.available = true;
    }
   
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
   
    public String checkAvailability() {
        return available ? "có sẵn" : "trống";
    }

    public void markAsBorrowed() {
        available = false;
    }

    public void markAsReturned() {
        available = true;
    }
 
    public void display() {
        System.out.println("Tên Sách: " + title);
        System.out.println("Mã: " + bookID);
        System.out.println("Tác giả: " + author);
        System.out.println("Trạng thái: " + checkAvailability());
    }
}