public class NameNumber {
    private String lastName;
    private String telNumber;

    public void NameNumber() {}

    public void  NameNumber(String lastName, String telNumber) {
        this.lastName = lastName;
        this.telNumber = telNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return telNumber;
    }

}
