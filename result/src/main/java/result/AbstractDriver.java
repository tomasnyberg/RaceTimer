package result;

import result.config.Config;
import util.TimeUtils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractDriver {
    private static final String missing = "MISSING";
    private static final String invalidTime = "--:--:--";
    private static final String missingStartTime = "Start?";
    private static final String missingEndTime = "Slut?";
    private static final String multipleStartTimes = "Flera starttider?";
    private static final String SEP = ";";

    private Config config = null;
    private List<String> startTimes = new ArrayList<>();
    private List<String> endTimes = new ArrayList<>();
    private String name = missing;
    private String driverNumber;
    private int maxLaps = 0;


    public AbstractDriver(String driverNumber, Config config) {
        this.driverNumber = driverNumber;
        this.config = config;
    }

    public String getDriverNumber() {
        return this.driverNumber;
    }

    // Set the name of this driver, match drivers with drivernumber
    public void setName(String name) {
        this.name = name;
    }

    public void addStartTime(String startTime) {
        startTimes.add(startTime);
    }

    public void addEndTime(String endTime) {
        endTimes.add(endTime);
    }

    // return difference between last goaltime and first starttime
    // return "--:--:--" if can't calculate
    protected String getTotalTime() {
        String totalTime = invalidTime;
        if (!endTimes.isEmpty() && !startTimes.isEmpty()) {
            totalTime = duration(startTimes.get(0), endTimes.get(endTimes.size() - 1));
        }

        return totalTime;
    }

    protected String duration(String start, String end) {
        LocalTime st = LocalTime.parse(start);
        LocalTime en = LocalTime.parse(end);
        return TimeUtils.formatTime(Duration.between(st, en));
    }

    protected String getStartTime() {
        return startTimes.isEmpty() ? missingStartTime : startTimes.get(0);
    }


    protected String getGoalTime() {
        return endTimes.isEmpty() ? missingEndTime : endTimes.get(endTimes.size()-1);
    }


    protected abstract String getErrors();

}


