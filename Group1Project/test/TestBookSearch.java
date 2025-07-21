
public class TestBookSearch {
    public static void test() {
        BookList bookList = new BookList();
        BookSearch bookSearch = new BookSearch(bookList);

        bookSearch.searchBook();
    }
}