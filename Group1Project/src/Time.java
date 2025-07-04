public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second){
       setTime(hour, minute, second);
    }

    public int getHour() {
        return hour;
    }
    public Time setHour(int h) {
        hour = (( h >= 0 && h < 24 ) ? h : 0 );
        return this;
    }
    public int getMinute() {
        return minute;
    }
    public Time setMinute(int m) {
        minute = (( m >= 0 && m < 60 ) ? m : 0 );
        return this;
    }
    
    public int getSecond() {
        return second;
    }
    public Time setSecond(int s) {
        second = ((s >= 0 && s < 60 ) ? s : 0 );
        return this;
    }
    public Time setTime(int h, int m, int s) {
        setHour(h);
        setMinute(m);
        setSecond(s);
        return this;
    }

    public String toString() {

        int displayHour = (hour == 0 || hour == 12) ? 12 : hour % 12;
        String hourStr = (displayHour < 10) ? "0" + displayHour : "" + displayHour;
        String amPm = (hour < 12) ? " AM" : " PM";
        String minuteStr = (minute < 10) ? "0" + minute : "" + minute;
        String secondStr = (second < 10) ? "0" + second : "" + second;
        return hourStr + ":" + minuteStr + ":" + secondStr + amPm;
    }
}
