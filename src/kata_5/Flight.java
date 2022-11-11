package kata_5;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Iterator;
import java.util.Locale;

public class Flight {
    private final DayOfWeek dayOfWeek;
    private final LocalTime depTime;
    private final int depDelay;
    private final LocalTime arrTime;
    private final int arrDelay;
    private final boolean cancelled;
    private final boolean diverted;
    private final int duration;
    private final int distance;

    public Flight(DayOfWeek dayOfWeek, LocalTime depTime, int depDelay, LocalTime arrTime, int arrDelay, boolean cancelled, boolean diverted, int duration, int distance) {
        this.dayOfWeek = dayOfWeek;
        this.depTime = depTime;
        this.depDelay = depDelay;
        this.arrTime = arrTime;
        this.arrDelay = arrDelay;
        this.cancelled = cancelled;
        this.diverted = diverted;
        this.duration = duration;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Flight{" + "dayOfWeek=" + dayOfWeek + ", depTime=" + depTime + ", depDelay=" + depDelay + ", arrTime=" + arrTime + ", arrDelay=" + arrDelay + ", cancelled=" + cancelled + ", diverted=" + diverted + ", duration=" + duration + ", distance=" + distance + '}';
    }

    String getDayOfWeek() {
        return this.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
    
    
}