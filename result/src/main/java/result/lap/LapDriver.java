package result.lap;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import result.AbstractDriver;
import result.config.Config;

/**
 * Class for representing one driver in a lap race
 */
public class LapDriver extends AbstractDriver {
    private List<String> lapTimes = new ArrayList<>();
    private int maxLaps = 0;

    /**
     *
     * @param driverNumber string with drivernumber
     * @param config a config file
     */
    public LapDriver(String driverNumber, Config config) {
        super(driverNumber, config);
        this.driverNumber = driverNumber;
        this.config = config;
    }

    /**
     *
     * @return Amount of laps driven by the driver
     */
    public int getAmountOfLaps() {
        return this.lapTimes.size() + (Math.min(1, this.goalTimes.size()));
    }

    /**
     * set maximum laps for a race
      * @param max maximum of laps
     */
    public void setMaxLaps(int max) {
        this.maxLaps = max;
    }

    /**
     * Adds a goal time to the race
     * @param goalTime string of the chosen goal time
     */
    public void addGoalTime(String goalTime) {
        if (!LocalTime.parse(config.getLap().getRaceEndTime()).isAfter(LocalTime.parse(goalTime)))
            goalTimes.add(goalTime);
        else
            lapTimes.add(goalTime);
    }

    /**
     *
     * @return the lap times for a driver
     */
    private List<String> generateLapTimes() {
        List<String> times = new ArrayList<>();
        times.add(getStartTime());
        times.addAll(lapTimes);
        if (!goalTimes.isEmpty())
            times.add(goalTimes.get(0));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < maxLaps; ++i) {
            if (i >= times.size() - 1 || times.get(i) == missingStartTime)
                result.add("");
            else
                result.add(duration(times.get(i), times.get(i + 1)));
        }
        return result;
    }

    /**
     * Returns all the lap times
      */

    private List<String> getLappings() {
        List<String> result = new ArrayList<>(lapTimes);
        while (result.size() < maxLaps - 1) {
            result.add("");
        }
        return result;
    }

    /**
     * Adds an error to the StringBuilder
     * @param times a list of all the invalid times
     * @param errorString a string of the specific error
     * @param sb a StringBuilder
     */
    private void errorAppender(List<String> times, String errorString, StringBuilder sb) {
        sb.append(errorString);
        for (int i = 1; i < times.size(); ++i) {
            sb.append(" ");
            sb.append(times.get(i));
        }
    }

    /**
     *  Creates an error string to append to the end of the toString, in the last column
     * @return a string of an error with the invalid times
     */
    public String getErrors() {

        boolean first = true;

        StringBuilder sb = new StringBuilder();
        if (generateLapTimes().stream().filter(x -> x != "")
                .anyMatch(x -> x.contains("-")
                        || LocalTime.parse(config.getLap().getMinimumTime()).isAfter(LocalTime.parse(x)))) {
            if (first)
                first = false;
            sb.append("Omöjlig varvtid?");
        }

        if (startTimes.size() > 1) {
            if (first)
                first = false;
            else
                sb.append(ERROR_SEP + " ");
            errorAppender(startTimes, multipleStartTimes, sb);
        }

        if (goalTimes.size() > 1) {
            if (!first)
                sb.append(ERROR_SEP + " ");
            errorAppender(goalTimes, multipleGoalTimes, sb);
        }

        return sb.toString().trim();
    }

    /**
     * The main part of this class. Creates the result row for the result file corresponding to this driver
     * @return A result row for a driver
     */
    @Override
    public String toString() {
        List<String> columns = new ArrayList<>(
                Arrays.asList(
                        driverNumber, name, "" + getAmountOfLaps(), getTotalTime()));
        columns.addAll(generateLapTimes());
        columns.add(getStartTime());
        columns.addAll(getLappings());
        columns.add(getGoalTime());

        if (!config.isSorting()) {
            columns.add(getErrors());
        }

        StringBuilder sb = new StringBuilder();
        for (var column : columns) {
            sb.append(column).append(SEP).append(' ');
        }

        // Remove the last separator and space
        String result = sb.substring(0, sb.length() - 2);
        result = result.endsWith("; ") ? result.substring(0, result.length() - 2) : result;

        if (config.isSorting()) {
            result = result.replace(missingStartTime, invalidTime).replace(missingGoalTime, invalidTime);
        }

        return result;
    }

    /**
     *
     * @param other an AbstractDriver
     * @return a comparison of amount of laps, total times and errors between two Lap drivers
     */
    @Override
    public int compareTo(AbstractDriver other) {
        if (!isErrors() && other.isErrors())
            return -1;

        if (isErrors() && !other.isErrors())
            return 1;

        if (getAmountOfLaps() == ((LapDriver) other).getAmountOfLaps()) {
            return getTotalTime().compareTo(other.getTotalTime());
        } else {
            return ((Integer) ((LapDriver) other).getAmountOfLaps()).compareTo((Integer) getAmountOfLaps());
        }
    }
}
