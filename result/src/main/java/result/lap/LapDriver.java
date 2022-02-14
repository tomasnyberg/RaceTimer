package result.lap;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import result.AbstractDriver;
import result.config.Config;
import util.TimeUtils;

public class LapDriver extends AbstractDriver {
    private List<String> lapTimes = new ArrayList<>();
    private int maxLaps = 0;

    // När man hittar ett drivernumber man inte sett innan skapar man en ny
    // lapDriver
    public LapDriver(String driverNumber, Config config) {
        super(driverNumber, config);
        this.driverNumber = driverNumber;
        this.config = config;
    }

    public int getAmountOfLaps() {
        return this.lapTimes.size() + (Math.min(1, this.goalTimes.size()));
    }

    // Ange hur många varv som är max för att formatera toString korrekt
    public void setMaxLaps(int max) {
        this.maxLaps = max;
    }

    public void addGoalTime(String goalTime) {
        if (!LocalTime.parse(config.getLap().getRaceEndTime()).isAfter(LocalTime.parse(goalTime)))
            goalTimes.add(goalTime);
        else
            lapTimes.add(goalTime);
    }

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

    // Return all the endtimes except for the last one
    private List<String> getLappings() {
        List<String> result = new ArrayList<>(lapTimes);
        while (result.size() < maxLaps - 1) {
            result.add("");
        }
        return result;
    }

    private void errorAppender(List<String> times, String errorString, StringBuilder sb) {
        sb.append(errorString);
        for (int i = 1; i < times.size(); ++i) {
            sb.append(" ");
            sb.append(times.get(i));
        }
        sb.append(" ");
    }

    // Append to the end of the toString, in the last column any errors
    // Example: 3; Chris Csson; 3; 01:03:06; 00:20:00; 00:20:00; 00:23:06; 12:02:00;
    // 12:22:00; 12:42:00; 13:05:06; Flera starttider? 12:05:00
    public String getErrors() {
        StringBuilder sb = new StringBuilder();
        if (generateLapTimes().stream().filter(x -> x != "")
                .anyMatch(x -> x.contains("-")
                        || LocalTime.parse(config.getLap().getMinimumTime()).isAfter(LocalTime.parse(x)))) {
            sb.append("Omöjlig varvtid? ");
        }

        if (startTimes.size() > 1) {
            errorAppender(startTimes, multipleStartTimes, sb);
        }

        if (goalTimes.size() > 1) {
            errorAppender(goalTimes, multipleGoalTimes, sb);
        }

        return sb.toString().trim();
    }

    /*
     * StartNr; Namn; #Varv; Totaltid; Varv1; Varv2; Varv3; Start; Varvning1;
     * Varvning2; Mål
     * Exempel på vad denna returnerar:
     * "1; Anders Asson; 3; 01:23:34; 00:30:00; 00:30:00; 00:23:34; 12:00:00; 12:30:00; 13:00:00; 13:23:34"
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
        columns.add(getErrors());

        StringBuilder sb = new StringBuilder();
        for (var column : columns) {
            sb.append(column).append(SEP).append(' ');
        }

        // Remove the last separator and space
        String result = sb.substring(0, sb.length() - 2);
        result = result.endsWith("; ") ? result.substring(0, result.length() - 2) : result;
        return result;
    }

    @Override
    public int compareTo(AbstractDriver other) {
        if (getErrors().isBlank() && !other.getErrors().isBlank())
            return 1;

        if (!getErrors().isBlank() && other.getErrors().isBlank())
            return -1;

        if (getAmountOfLaps() == ((LapDriver) other).getAmountOfLaps()) {
            return getTotalTime().compareTo(other.getTotalTime());
        } else {
            return ((Integer) ((LapDriver) other).getAmountOfLaps()).compareTo((Integer) getAmountOfLaps());
        }
    }
}
