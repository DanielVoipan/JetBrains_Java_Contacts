
class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        if (minutes == 59) {
            addHour(hours, this);
            minutes = 0;
        } else {
            minutes++;
        }
    }

    static void addHour(int hours, Clock clock) {
        if (hours == 12) {
            clock.hours = 1;
        } else {
            clock.hours++;
        }
    }
}
