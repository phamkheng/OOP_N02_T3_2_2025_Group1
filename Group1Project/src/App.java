public class App {
    public static void main(String[] args) throws Exception {
        TestLibraryBook.test();
        TestLibraryReader.test();
        TestLibraryLoan.test();
        TestRegisterReader.test();
        TestLoanManager.test(args);
    }
}