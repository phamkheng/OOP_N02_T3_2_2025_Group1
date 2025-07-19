public class Book1 {
    public void show(Book1 b) {
        System.out.println("Đây là một cuốn sách.");
    }
}

class Textbook extends Book1 {

    public void show(Book1 b) {
        System.out.println("Đây là sách giáo khoa.");
    }
}

class Novel extends Book1 {

    public void show(Book1 b) {
        System.out.println("Đây là tiểu thuyết.");
    }
}

class Book1Demo {
    public static void book() {
        Book1 display = new Book1();
        Book1 b;

        double d = Math.random();  

        if (d > 0.5)
            b = new Textbook();  
        else
            b = new Novel();     
        display.show(b);  
    }
}