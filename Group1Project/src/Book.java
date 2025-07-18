public class Book {
    String bookID;
    String title;
    String author;
    boolean isAvailable;

    public Book() {
        this.isAvailable = true;
    }

    public Book(String bookID, String title, String author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void markAsBorrowed() {
        this.isAvailable = false;
    }

    public void markAsReturned() {
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    // Thêm các getter/setter cần thiết cho LibraryApp
    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Thêm hàm hiển thị thông tin sách
    public void display() {
        System.out.println("ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable);
    }
}

