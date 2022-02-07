package result.Varvlopp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarvloppDriver {
    private static final String missing = "MISSING";
    private static final String invalidTime = "--:--:--";
    private static final String SEP = ";";

    private List<String> startTimes = new ArrayList<>();
    private List<String> endTimes = new ArrayList<>();
    private String name = missing;
    private String driverNumber;
    private int maxLaps = 0;

    // När man hittar ett drivernumber man inte sett innan skapar man en ny
    // varvloppdriver
    public VarvloppDriver(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverNumber() {
        return this.driverNumber;
    }

    public int getAmountOfLaps() {
        return this.endTimes.size();
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
        endTimes.add(endTime);
    }

    // return difference between last goaltime and first starttime
    // return "--:--:--" if can't calculate
    private String getTotalTime() {
        return "TODO";
    }

    private List<String> generateVarvTimes() {
        return new ArrayList<>();
    }

    private String getStartTime() {
        return "TODO";
    }

    // Return all the endtimes except for the last one
    private List<String> getLappings() {
        return new ArrayList<>();
    }

    private String getGoalTime() {
        return "TODO";
    }

    // V4 TODO
    // Append to the end of the toString, in the last column any errors
    // Example: 3; Chris Csson; 3; 01:03:06; 00:20:00; 00:20:00; 00:23:06; 12:02:00;
    // 12:22:00; 12:42:00; 13:05:06; Flera starttider? 12:05:00
    private String getErrors() {
        return "";
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
                driverNumber, name, "" + endTimes.size(), getTotalTime()
            )
        );
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

}