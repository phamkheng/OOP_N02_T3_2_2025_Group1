public class App {
    public static void main(String[] args) throws Exception {

        TestBookSearch.test();
        TestLoanManager.test(args);
        TestRegisterReader.test();
        TestReturnProcess.test();
        
        TestLibraryBook.test();
        TestLibraryReader.test();
        TestLibraryLoan.test();

    }
}