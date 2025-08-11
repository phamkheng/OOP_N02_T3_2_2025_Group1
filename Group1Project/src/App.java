import Controller.LibraryReader;
import test.TestLibraryReader;
import test.TestRegisterReader;

public class App {
    public static void main(String[] args) throws Exception {

        LibraryReader khang = new LibraryReader();
        khang.inputReaders();
        khang.readReaders();
        khang.searchReaderByName();
        khang.deleteReaderFlexible();
    }
}