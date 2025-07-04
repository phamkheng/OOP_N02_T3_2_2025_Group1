
public class App {
    public static void main(String[] args) throws Exception {

    Time t = new Time(20, 3, 45);
    System.out.println(t.toString());
    
    Recursion r = new Recursion();
    System.out.println(r.factorial(12));

    }
}