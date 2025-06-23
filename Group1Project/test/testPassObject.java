public class testPassObject {
    static void f(Number m) {
        m.i = 15;
    }

    public void PassObject() {
        Number n = new Number();
        n.i = 14;
        testPassObject.f(n);
        System.out.println(n.i);
    }
}
