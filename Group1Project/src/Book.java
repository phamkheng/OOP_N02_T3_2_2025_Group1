public class Book {
    String title;
    String author;
    Boolean available;

    public Book() {}

    public Book(String title, String author, Boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public Boolean getAvailable() {
        return available;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (available ? " available" : "tmpty"));
    }
}


