
import Model.LibraryObject;

public class TestLibraryObject {
    public static void test() {
        // Tạo anonymous subclass để cài đặt printInfo()
        LibraryObject obj = new LibraryObject() {
            
            public void printInfo() {
                System.out.println("=== LibraryObject.printInfo() ===");
                System.out.println("Name     : " + getName());
                System.out.println("ID       : " + getID());
                System.out.println("Category : " + getCategory());
                System.out.println("Status   : " + getStatus());
            }
        };

        obj.setName("Test Object");
        obj.setID(999);
        obj.setCategory("DemoCategory");
        obj.setStatus(1);

        obj.printInfo();
    }
}
