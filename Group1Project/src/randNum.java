import java.util.Random;

public class randNum {
    public int randomNumber() {
        Random r = new Random();
        return r.nextInt(1000);
    }

    public void display() {
        int num = randomNumber();
        System.out.println("Random number: " + num);
    }
}
