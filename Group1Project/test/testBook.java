public class testBook {
    String title;
    String author;
    int numPages;
    
    testBook() {}
    public testBook(String title, String author, int numPages) {
        this.title = title;
        this.author = author;
        this.numPages = numPages;
    }
    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Pages: " + numPages);
    }
}
