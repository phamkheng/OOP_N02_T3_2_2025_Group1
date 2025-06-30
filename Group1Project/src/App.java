public class App {
    private String name;
    private String version;
    private String developer;

    public App() { }

    public App(String name, String version, String developer) {
        this.name = name;
        this.version = version;
        this.developer = developer;
    }

    public String getName() { return name; }
    public String getVersion() { return version; }
    public String getDeveloper() { return developer; }

    public void setName(String name) { this.name = name; }
    public void setVersion(String version) { this.version = version; }
    public void setDeveloper(String developer) { this.developer = developer; }
}