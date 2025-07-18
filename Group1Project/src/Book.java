class Book {
    String bookID;
    String title;
    String author;

    public Book(String bookID, String title, String author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
    }

    public String getBookID(){
        return bookID;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
}
