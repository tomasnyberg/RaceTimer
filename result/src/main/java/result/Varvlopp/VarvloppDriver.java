package result.Varvlopp;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import result.config.Config;
import util.TimeUtils;

public class VarvloppDriver implements Comparable<VarvloppDriver> {
    private static final String missing = "MISSING";
    private static final String invalidTime = "--:--:--";
    private static final String missingStartTime = "Start?";
    private static final String missingEndTime = "Slut?";
    private static final String multipleStartTimes = "Flera starttider?";
    private static final String multipleGoalTimes = "Flera måltider?";
    private static final String SEP = ";";
    private static final String ERROR_SEPARATOR = ",";

    private Config config = null;
    private List<String> startTimes = new ArrayList<>();
    private List<String> lapTimes = new ArrayList<>();
    private List<String> goalTimes = new ArrayList<>(); 
    private String name = missing;
    private String driverNumber;
    private int maxLaps = 0;

    // När man hittar ett drivernumber man inte sett innan skapar man en ny
    // varvloppdriver
    public VarvloppDriver(String driverNumber) {
        this.driverNumber = driverNumber;
    }
    public VarvloppDriver(String driverNumber, Config config) {
        this.driverNumber = driverNumber;
        this.config = config;
    }

    public String getDriverNumber() {
        return this.driverNumber;
    }

    public int getAmountOfLaps() {
        return this.lapTimes.size() + (Math.min(1, this.goalTimes.size()));
    }

    // Ange hur många varv som är max för att formatera toString korrekt
    public void setMaxLaps(int max) {
        this.maxLaps = max;
    }

    // Set the name of this driver, match drivers with drivernumber
    public void setName(String name) {
        this.name = name;
    }

    public void addStartTime(String startTime) {
        startTimes.add(startTime);
    }

    public void addEndTime(String endTime) {
        if (!LocalTime.parse(config.getVarv().getRaceEndTime()).isAfter(LocalTime.parse(endTime)))
            goalTimes.add(endTime);
        else
            lapTimes.add(endTime);
    }

    // return difference between last goaltime and first starttime
    // return "--:--:--" if can't calculate
    private String getTotalTime() {
        String totalTime = invalidTime;
        if (!goalTimes.isEmpty() && !startTimes.isEmpty()) {
            totalTime = duration(startTimes.get(0), goalTimes.get(0));
        }

        return totalTime;
    }

    private String duration(String start, String end) {
        LocalTime st = LocalTime.parse(start);
        LocalTime en = LocalTime.parse(end);
        return TimeUtils.formatTime(Duration.between(st, en));
    }

    private List<String> generateVarvTimes() {
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

    private String getStartTime() {
        return startTimes.isEmpty() ? missingStartTime : startTimes.get(0);
    }

    // Return all the endtimes except for the last one
    private List<String> getLappings() {
        List<String> result = new ArrayList<>(lapTimes);
        while(result.size() < maxLaps - 1){
            result.add("");
        }
        return result;
    }

    private String getGoalTime() {
        return (goalTimes.isEmpty()) ? missingEndTime : goalTimes.get(0);
    }

    // V4 TODO
    // Append to the end of the toString, in the last column any errors
    // Example: 3; Chris Csson; 3; 01:03:06; 00:20:00; 00:20:00; 00:23:06; 12:02:00;
    // 12:22:00; 12:42:00; 13:05:06; Flera starttider? 12:05:00
    private String getErrors() {
        boolean first = true;
        StringBuilder sb =  new StringBuilder();
        if (
            generateVarvTimes().stream().filter(x -> x != "")
                .anyMatch(x -> x.contains("-") || LocalTime.parse(config.getVarv().getMinimumTime()).isAfter(LocalTime.parse(x)))
            ) {
            if(first) first = false;
            sb.append("Omöjlig varvtid?");
        }
        
        if (startTimes.size() > 1){
            if(first) first = false;
            else sb.append(ERROR_SEPARATOR + " ");
            sb.append(multipleStartTimes);
            for (int i = 1; i<startTimes.size(); ++i){
                sb.append(" ");
                sb.append(startTimes.get(i));
            }
        }
        
        if (goalTimes.size() > 1){
            if(!first) sb.append(ERROR_SEPARATOR + " ");
            sb.append(multipleGoalTimes);
            for (int i = 1; i < goalTimes.size(); ++i){
                sb.append(" ");
                sb.append(goalTimes.get(i));
            }
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
        columns.addAll(generateVarvTimes());
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
    public int compareTo(VarvloppDriver other) {
        if(missingStartOrEnd() && !other.missingStartOrEnd()) return 1;

        if(!missingStartOrEnd() && other.missingStartOrEnd()) return -1;

        if(getAmountOfLaps() == other.getAmountOfLaps()) {
            return getTotalTime().compareTo(other.getTotalTime());
        } else {
            return ((Integer) other.getAmountOfLaps()).compareTo((Integer) getAmountOfLaps());
        }
    }

    private boolean missingStartOrEnd () {
        return getStartTime().equals(invalidTime);
    }
}
