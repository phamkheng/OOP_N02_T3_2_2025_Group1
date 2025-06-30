public class TestBook {
    public static void main(String[] args) {
        Book myBook = new Book("Java OOP", "Tran Quoc Viet Hung", 300);

        System.out.println("Title: " + myBook.getTitle());
        System.out.println("Author: " + myBook.getAuthor());
        System.out.println("Pages: " + myBook.getNumPages());
    }
}
   