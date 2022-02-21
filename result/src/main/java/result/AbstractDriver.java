package result;

import result.config.Config;
import util.TimeUtils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for the drivers in a race. 
 */
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

    /**
     * 
     * @param driverNumber A string for the driver number
     * @param config A config file
     */

    public AbstractDriver(String driverNumber, Config config) {
        this.driverNumber = driverNumber;
        this.config = config;
    }

    /**
     * 
     * @return Returns the drivers driver number
     */

    public String getDriverNumber() {
        return this.driverNumber;
    }

    /**
     * Sets the name of the driver
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a start time to the startTimes list
     * @param startTime A string representing a startTime with the format "HH:MM:SS"
     */
    public void addStartTime(String startTime) {
        startTimes.add(startTime);
    }

    /**
     * Adds a goal time to the goalTimes list
     * @param goalTime A string representing a goal time
     */
    public void addGoalTime(String goalTime) {
        goalTimes.add(goalTime);
    }

    /**
     * Calculates total time for a driver
     * @return Returns difference between last goaltime and first starttime. Returns "--:--:--" if can't calculate
     */
    public String getTotalTime() {
        String totalTime = invalidTime;
        if (!goalTimes.isEmpty() && !startTimes.isEmpty()) {
            totalTime = duration(startTimes.get(0), goalTimes.get(0));
        }

        return totalTime;
    }

    /**
     * Checks if a drivers registered goal time is before its registered start time
     * @return true if goal time is before start time, false otherwise
     */
    protected boolean goalTimeBeforeStartTime() {
        return LocalTime.parse(getGoalTime()).isBefore(LocalTime.parse(getStartTime()));
    }

    /**
     * Method for calculating time duration between two time stamps
     * @param start Start time stamp
     * @param end End time stamp
     * @return String representation of duration between the two times
     */
    protected String duration(String start, String end) {
        LocalTime st = LocalTime.parse(start);
        LocalTime en = LocalTime.parse(end);
        return TimeUtils.formatTime(Duration.between(st, en));
    }

    /**
     * If startTime list is empty, the error string missingStartTime is returned, otherwise the first time in startTimes is returned
     * @return Either missingStartTime or String representation of first start time
     */
    protected String getStartTime() {
        return startTimes.isEmpty() ? missingStartTime : startTimes.get(0);
    }

    /**
     * If goalTime list is empty, the error string missingGoalTime is returned, otherwise the first goal time in goalTimes is returned
     * @return Error string or goal time
     */
    protected String getGoalTime() {
        return (goalTimes.isEmpty()) ? missingGoalTime : goalTimes.get(0);
    }
    
    public abstract String getErrors();

    /**
     * Checks if one driver has any errors
     * @return true if driver has any errors, false otherwise
     */
    public boolean isErrors() {
        return getGoalTime().equals(missingGoalTime) ||
                getStartTime().equals(missingStartTime) ||
                !(getErrors().isBlank());
    }

}
