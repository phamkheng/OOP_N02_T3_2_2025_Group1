import java.util.ArrayList;
public class App {
    public static void main(String[] args) throws Exception {
    
    //User u = new User("Khang", "24100032", "phamnangkahng@gmail.com");
    //u.display();

    //Time t = new Time(20, 3, 45); 
    //System.out.println(t.toString());
    
    //Recursion r = new Recursion(); // Đệ qui
   // System.out.println(r.factorial(12));
        Book t1 = new Book("How to Win Friends and Influence People", "Dale Carnegie", true);
        Book t2 = new Book("OOP_01", "Khang_Hung_Khoa", true);
        ArrayList<Book> ListBook = new ArrayList<Book>();
        ListBook.add(t1);
        ListBook.add(t2);

        System.out.println("Name Book: " + ListBook.get(0).getTitle());
        System.out.println("Author: " + ListBook.get(0).getAuthor());
        System.out.println("Satus: " + ( ListBook.get(0).getavailable() ? "available" : "empty"));

    }
}