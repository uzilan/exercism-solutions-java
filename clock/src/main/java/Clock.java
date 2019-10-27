import java.util.Objects;

public class Clock {
    private int hours;
    private int minutes;

    public Clock(final int hours, final int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        adjustTime();
    }

    public void add(final int minutes) {
        this.minutes += minutes;
        adjustTime();
    }

    @Override
    public String toString() {
        String mm;
        String hh;

        if (hours == 0) {
            hh = "00";
        } else if (hours < 10) {
            hh = "0" + hours;
        } else {
            hh = "" + hours;
        }

        if (minutes == 0) {
            mm = "00";
        } else if (minutes < 10) {
            mm = "0" + minutes;
        } else {
            mm = "" + minutes;
        }

        return hh + ":" + mm;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Clock clock = (Clock) o;
        return hours == clock.hours && minutes == clock.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes);
    }

    private void adjustTime() {
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes %= 60;
        }

        while (minutes < 0) {
            hours--;
            minutes += 60;
        }

        if (hours >= 24) {
            hours %= 24;
        }

        while (hours < 0) {
            hours += 24;
        }
    }
}
