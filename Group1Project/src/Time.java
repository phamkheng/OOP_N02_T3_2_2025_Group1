public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }
    public int setHour(int hour) {
        this.hour = hour;
        return (hour >= 0 && hour <= 24) ? hour : 0;
    }
    public int getMinute() {
        return minute;
    }
    public int setMinhute(int minute) {
        this.minute = minute;
        return (minute >= 0 && minute <= 60) ? minute : 0;
    }
    public int getSecond() {
        return second;
    }
    public int setSecond(int second) {
        this.second = second;
        return (second >= 0 && second <= 60) ? second : 0;
    }

    void display() {
		String hour = this.hour + "";
		String minute = this.minute + "";
		String second = this.second + "";
		if (hour.length() == 1)
			hour = "0" + hour;
		if (minute.length() == 1)
			minute = "0" + minute;
		if (second.length() == 1)
			second = "0" + second;
		System.out.println("Time " + hour + ":" + minute + ":" + second);
	}


}
