public class Time {
    private int hour;
    private int day;
    private int month;
    private int year;

    public Time() {
        this.hour = 0;
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public void setTime(int hour, int day, int month, int year) {
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public void timeForward(int amount) {
        this.hour += amount;
        while (this.hour >= 24) {
            this.hour = this.hour - 24;
            this.day++;
            if (this.day >= 30) {
                this.day = this.day - 30;
                this.month++;
                if (this.month >= 12) {
                    this.month = this.month - 12;
                    this.year++;
                }
            }
        }
        System.out.println("\n ~Hour:" + this.hour + " Day:" + this.day + " Month:" + this.month + " Year:" + this.year + "\n");
    }
    public void clearTime() {
        this.hour = 0;
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }
    public void timeBackward(int amount) {
        this.hour -= amount;
        if (this.hour == -1) {
            this.hour = 23;
            this.day--;
            if (this.day == 0) {
                this.day = 29;
                this.month--;
                if (this.month == 0) {
                    this.month = 12;
                }
            }
        }
    }

    public void printTime() {
        System.out.println(this.hour + ":" + this.day + ":" + this.month + ":" + this.year);
    }
    public int getHour() {
        return hour;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
