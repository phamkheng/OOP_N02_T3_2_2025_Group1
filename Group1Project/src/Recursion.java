public class Recursion {
    private long number;

    public long getnumber() {
        return number;
    }
    public void setNumber(long number) {
        this.number = number;
    }

    public long factorial(long number) {
        if(number <= 1) return 1;
        else return number * factorial(number - 1);
    }

}
