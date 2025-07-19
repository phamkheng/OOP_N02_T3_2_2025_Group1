import java.util.ArrayList;
import java.util.Scanner;

public class LibraryReader {

    ArrayList<Reader> listReaders = new ArrayList<>();

    public ArrayList<Reader> addReader(Reader r) {
        try {
            listReaders.add(r);
        } catch (Exception e) {
            System.out.println("Error adding reader: " + e.getMessage());
        }
        return listReaders;
    }

    public ArrayList<Reader> deleteReader(String readerId) {
        try {
            for (int i = 0; i < listReaders.size(); i++) {
                if (listReaders.get(i).readerID.equals(readerId)) {
                    listReaders.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error deleting reader: " + e.getMessage());
        }
        return listReaders;
    }

    public void readReaders() {
        try {
            for (Reader r : listReaders) {
                System.out.println("Reader ID: " + r.readerID);
                System.out.println("Name: " + r.name);
                System.out.println("Email: " + r.email);
                System.out.println("Phone: " + r.phone);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error reading readers: " + e.getMessage());
        }
    }

    public ArrayList<Reader> editReader(String readerId, Scanner sc) {
        try {
            for (Reader r : listReaders) {
                if (r.readerID.equals(readerId)) {
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter new phone: ");
                    String newPhone = sc.nextLine();

                    r.name = newName;
                    r.email = newEmail;
                    r.phone = newPhone;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error editing reader: " + e.getMessage());
        }
        return listReaders;
    }
}
