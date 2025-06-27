
import java.lang.Integer;
public class testNumber {
    public void test() {

        Number n1 = new Number(47);


       // Integer i1 = new Integer(47);



        Number n2 = new Number(10);
        n1.i = 2;
        n2.i = 5;
        n1 = n2;
        n2.i = 10;

        // what is n1.i ?
        System.out.println("n1.i = " + n1.i);

        n1.i = 20;
        // what is n2.i ?

        System.out.println("n2.i = " + n2.i);

    }

    public void testSaThu() {

        Number n1 = new Number(74);
        Number n2 = new Number(14);
        n1.i = 2;
        n2.i = 5;

        n1 = n2;
        n2.i = 10;
        // what is n1.i?
        System.out.println("test cua Sa thu" + n1.i);

        n1.i = 20;
        // what is n2.i?
        System.out.println(n2.i);
    }

}
