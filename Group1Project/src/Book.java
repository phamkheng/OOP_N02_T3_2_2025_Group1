public class Book {
    String book_id;
    String book_title;
    String author;
    String genre;
    Boolean avaiable;

    String setName(String name_book) {

        book_title = name_book;
        return book_title;
    }

    void getName() {
        System.out.println("Ten sach:" + book_title);
    }
}
