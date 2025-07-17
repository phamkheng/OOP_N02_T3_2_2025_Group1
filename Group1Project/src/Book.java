public class Book {
    private String title;
    private String author;
    private Boolean available;

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

    public Boolean getavailable() {
        return available;
    }


    @Override
    public String toString() {
        return "Tiêu đề: " + title + ", Tác giả: " + author;
    }
}
