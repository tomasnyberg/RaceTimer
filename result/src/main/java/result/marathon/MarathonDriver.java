package result.marathon;

import result.Driver;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

/**
 * Marathon implementation of the Driver class.
 */
public class MarathonDriver extends Driver {

    private List<LocalTime> startTimes = new ArrayList<LocalTime>();
    private List<LocalTime> endTimes = new ArrayList<LocalTime>();

    public MarathonDriver(String startNumber) {
        super(startNumber);
    }

    public LocalTime getStart() {
        if (startTimes.isEmpty())
            return null;
        return startTimes.get(0);
    }

    public List<LocalTime> getStartTimes() {
        return startTimes;
    }

    public void registerStart(LocalTime start) {
        startTimes.add(start);
    }

    public LocalTime getEnd() {
        if (endTimes.isEmpty())
            return null;
        return endTimes.get(0);
    }

    public List<LocalTime> getEndTimes() {
        return endTimes;
    }

    public void registerEnd(LocalTime end) {
        endTimes.add(end);
    }

}
