
public class App {
    public static void main(String[] args) throws Exception {
        //testNumber test = new testNumber();

        //testCakeCase test = new testCakeCase();
        //test.cakecase();

        //randNum test = new randNum();
        //test.display();
  
        //ShortCircuit test = new ShortCircuit();
        //test.display();

        //testBook test = new testBook("Solo_leveling", "Chugong", 270);
        //test.display();

        //Leaf x = new Leaf();
        //x.increment().increment().increment().print();

        User u = new User("khang","4143425","phamnangkhang@gmail.com");
        u.display();
        System.out.println(u.getName());

        Time t = new Time(20, 3 ,45);
        t.display();
    }
}
