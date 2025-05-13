public class Time {



    private int hour;
    private int day;
    private int month;
    private int year;

    private int weekDay;
    private int week;

    public Time() {
        this.hour = 0;
        this.day = 0;
        this.month = 0;
        this.year = 0;
        this.weekDay = 0;
        this.week = 0;
    }

    public void setTime(int hour, int day, int month, int year) {
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public void timeForward(Player player, int amount) {
        this.hour += amount;
        while (this.hour >= 24) {

            this.hour = this.hour - 24;
            this.day++;
            this.weekDay++; // used for weeks

            if (this.weekDay >= 7) {
                this.weekDay = 0;
                this.week++;   // weeks not used for anything other than recording
                updates(player);
            }

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


    private static void updates(Player player) {

        System.out.println("\n ~It's been a week!");

        double interest = player.getGold() * 0.0001;  // Calculate interest on gold
        if (interest > 0.1) { // Max interest is 10% (1000 gold)
            interest = 0.1;
        }
        if (interest > 0) {
            double resDouble = (double) player.getMoney() * interest; // Cast to double
            int res = (int) resDouble; // Cast back to int if you need an integer result
            player.setMoney(player.getMoney() + res);
            System.out.println("Your interest on gold: " + res + " bucks. Interest: " + (interest * 100) + "%" );
        }

        if (player.getWarriors() > 0) {
            player.setLand(player.getLand() + player.getWarriors()); // interest on gold
            System.out.println("Your warriors have conquered: " + player.getWarriors() + " acres.");
        }
    }

}
