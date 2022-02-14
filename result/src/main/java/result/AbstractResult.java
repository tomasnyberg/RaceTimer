package result;

import result.lap.LapDriver;
import result.config.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResult {
    public List<AbstractDriver> drivers;
    protected Config config;

    public AbstractResult(Config config){
        this.drivers = new ArrayList<>();
        this.config = config;
    }

    public abstract void generateResult();

    // both read start times and end times read the times, so we have a method for the common things
    // returns something that the other methods can use to fill in the info for the drivers
    protected abstract void readTimes(String filePath, boolean start);

    // Reads starttimes from a starttimefile, and sets the start times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    protected abstract void readStartTimes();

    // Reads endtimes from a file, and adds the end times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    protected abstract void readEndTimes();


    // Reads names from a file
    // If we have not seen this drivernumber so far, we create a new driver
    protected abstract void readNames();

    protected List<String> readFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> list = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            return list;
        } catch (Exception e) {
            System.out.println("Could not find " + path);
            return null;
        }
    }

}


