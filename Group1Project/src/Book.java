public class Book {
    String title;
    String author;
    int numPages;
<<<<<<< HEAD
    Book() {}
=======

    public Book() { }

>>>>>>> 22262b4e7c7264badbc0fd07c5e1ba51380793b7
    public Book(String title, String author, int numPages) {
        this.title = title;
        this.author = author;
        this.numPages = numPages;
    }
<<<<<<< HEAD
    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Pages: " + numPages);
    }
}
=======

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getNumPages() { return numPages; }
}
>>>>>>> 22262b4e7c7264badbc0fd07c5e1ba51380793b7
