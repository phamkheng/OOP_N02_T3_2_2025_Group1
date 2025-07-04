
public class App {
    public static void main(String[] args) throws Exception {
    
    User u = new User("Khang", "24100032", "phamnangkahng@gmail.com");
    u.display();

    Time t = new Time(20, 3, 45); 
    System.out.println(t.toString());
    
    Recursion r = new Recursion(); // Đệ qui
    System.out.println(r.factorial(12));

    }
}