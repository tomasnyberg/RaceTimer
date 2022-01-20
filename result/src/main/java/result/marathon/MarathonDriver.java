package result.marathon;

import result.Driver;

import java.time.LocalTime;

/**
 * Marathon implementation of the Driver class.
 */
public class MarathonDriver extends Driver {

    private LocalTime start;
    private LocalTime end;

    public MarathonDriver(String startNumber) {
        super(startNumber);
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

}
