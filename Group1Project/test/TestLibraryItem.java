import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

import Model.LibraryItem;
public class TestLibraryItem {
    public static void test() {
        LibraryItem b1 = new LibraryItem("code", 5);
        LibraryItem b2 = new LibraryItem("OPP", 2);
        LibraryItem b3 = new LibraryItem("làm sao qua môn cô thu", 8);

        if (b2.compareTo(b1) < 0)
            System.out.println(b2.getTitle() + " has less quantity than " + b1.getTitle());

        Set<LibraryItem> librarySet = new TreeSet<>();
        librarySet.add(b1);
        librarySet.add(b2);
        librarySet.add(b3);

        Iterator i = librarySet.iterator();
        while (i.hasNext()) {
            System.out.println(((LibraryItem)i.next()).getTitle());
        }
    }
}
