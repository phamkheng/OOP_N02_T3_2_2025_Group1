import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestReturnProcess {
    public static void main(String[] args) {
        System.out.println("=== Kich ban 1: Nhap dung L001 ===");
        simulateInput("L001\n");

        System.out.println("\n=== Kich ban 2: Nhap sai L999, chon yes, nhap dung L001 ===");
        simulateInput("L999\nyes\nL001\n");

        System.out.println("\n=== Kich ban 3: Nhap sai L999, chon no ===");
        simulateInput("L999\nno\n");
    }

    private static void simulateInput(String input) {
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            ReturnProcess.main(new String[0]);
        } finally {
            System.setIn(originalIn); // khoi phuc input mac dinh sau moi test
        }
    }
}
