
public class TestRegisterReader {
    public static void test() {
        RegisterReader register = new RegisterReader();
        register.registerNewReader();

        System.out.println("Danh sách bạn đọc hiện tại:");
        for (Reader r : register.getReaders()) {
            System.out.println(r);
        }
    }
}