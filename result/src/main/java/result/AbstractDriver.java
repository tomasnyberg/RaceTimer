package result;

import result.config.Config;
import util.TimeUtils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDriver implements Comparable<AbstractDriver> {
    protected static final String missing = "MISSING";
    protected static final String invalidTime = "--:--:--";
    protected static final String missingStartTime = "Start?";
    protected static final String missingGoalTime = "Slut?";
    protected static final String multipleStartTimes = "Flera starttider?";
    protected static final String multipleGoalTimes = "Flera måltider?";
    protected static final String SEP = ";";
    protected static final String ERROR_SEP = ",";

    protected Config config = null;
    protected List<String> startTimes = new ArrayList<>();
    protected List<String> goalTimes = new ArrayList<>();
    protected String name = missing;
    protected String driverNumber;

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

    public void addGoalTime(String goalTime) {
        goalTimes.add(goalTime);
    }

    // return difference between last goaltime and first starttime
    // return "--:--:--" if can't calculate
    public String getTotalTime() {
        String totalTime = invalidTime;
        if (!goalTimes.isEmpty() && !startTimes.isEmpty()) {
            totalTime = duration(startTimes.get(0), goalTimes.get(0));
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
        return (goalTimes.isEmpty()) ? missingGoalTime : goalTimes.get(0);
    }

    public abstract String getErrors();

}
