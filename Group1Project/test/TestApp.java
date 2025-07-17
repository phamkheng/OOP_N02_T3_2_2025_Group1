public class TestApp {
    public static void testApp() {
        App myApp = new App("LINKS", "1.0", "Quan-Ly-Thu-Vien-JavaSwing");

        System.out.println("App Name: " + myApp.getName());
        System.out.println("Version: " + myApp.getVersion());
        System.out.println("Developer: " + myApp.getDeveloper());

        myApp.setVersion("2.0");
        System.out.println("Updated Version: " + myApp.getVersion());
    }
}
