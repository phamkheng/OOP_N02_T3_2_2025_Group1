import java.util.ArrayList;
import java.util.Scanner;
public class LibraryReader {

    ArrayList<Reader> listReaders = new ArrayList<>();

    public ArrayList<Reader> addReader(Reader r) {
        listReaders.add(r);
        return listReaders;
    }

    public ArrayList<Reader> deleteReader(String readerId) {
        for (int i = 0; i < listReaders.size(); i++) {
            if (listReaders.get(i).readerID.equals(readerId)) {
                listReaders.remove(i);
                break;
            }
        }
        return listReaders;
    }

    public void readReaders() {
        for (Reader r : listReaders) {
            System.out.println("Reader ID: " + r.readerID);
            System.out.println("Name: " + r.name);
            System.out.println("Email: " + r.email);
            System.out.println("Phone: " + r.phone);
            System.out.println();
        }
    }

    public ArrayList<Reader> editReader(String readerId) {
        Scanner sc = new Scanner(System.in);
        sc.close();
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
        return listReaders;
    }
} 