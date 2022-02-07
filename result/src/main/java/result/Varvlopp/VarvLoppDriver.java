package result.Varvlopp;

import java.util.ArrayList;
import java.util.List;

public class VarvLoppDriver {
    private List<String> startTimes = new ArrayList<>();
    private List<String> endTimes = new ArrayList<>();
    private String name;
    private String driverNumber;
    private int maxLaps;

    // När man hittar ett drivernumber man inte sett innan skapar man en ny
    // varvloppdriver
    public VarvLoppDriver(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverNumber(){
        return this.driverNumber;
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

    private String generateVarvTimes() {
        return "TODO";
    }

    private String getStartTime() {
        return "TODO";
    }

    // Return all the endtimes except for the last one
    private List<String> getLappings() {
        return null;
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

    @Override
    public String toString() {
        /*
         * StartNr; Namn; #Varv; Totaltid; Varv1; Varv2; Varv3; Start; Varvning1;
         * Varvning2; Mål
         * Exempel på vad denna returnerar:
         * "1; Anders Asson; 3; 01:23:34; 00:30:00; 00:30:00; 00:23:34; 12:00:00; 12:30:00; 13:00:00; 13:23:34"
         */
        return driverNumber + name + endTimes.size() + getTotalTime() + generateVarvTimes() + getStartTime()
                + getLappings()
                + getGoalTime() + getErrors();
    }

}