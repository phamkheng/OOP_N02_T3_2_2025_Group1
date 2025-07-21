public class App {
    public static void main(String[] args) throws Exception {
        TestBookSearch.test();
        TestRegisterReader.test();
        TestLoanManager.test(args);
        TestReturnProcess.test();


    }
}