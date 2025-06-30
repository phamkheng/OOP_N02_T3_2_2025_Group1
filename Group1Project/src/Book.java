public class Book {
    String title;
    String author;
    int numPages;

    public Book() { }

    public Book(String title, String author, int numPages) {
        this.title = title;
        this.author = author;
        this.numPages = numPages;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getNumPages() { return numPages; }
}