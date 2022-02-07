package result.Varvlopp;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class VarvloppResult {
    public List<VarvloppDriver> drivers;

    public VarvloppResult(){
        this.drivers = new ArrayList<>();
    }

    // The only public method visible, reads in all the files and generates the result txt file.
    // Readds from args if they exist for specifying where files are, otherwise from config
    // Takes in a config class (TODO) where we get the file paths etc.
    public void generateResult(){

    }

    // both read start times and end times read the times, so we have a method for the common things
    // returns something that the other methods can use to fill in the info for the drivers
    private void readTimes(){

    }

    // Reads starttimes from a starttimefile, and sets the start times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    public void readStartTimes(String startTimeFile){
        List<String> lines = readFile(startTimeFile);
        for(String line: lines){
            String[] split = line.split("; ");
            String time = split[1];
            String driverNumber = split[0];
            boolean found = false;
            for(int i = 0; i < drivers.size(); i++){
                if(drivers.get(i).getDriverNumber().equals(driverNumber)){
                    drivers.get(i).addStartTime(time);
                    found = true;
                    break;
                }
            }
            if(!found){
                VarvloppDriver driver = new VarvloppDriver(driverNumber);
                driver.addStartTime(time);
                drivers.add(driver);
            }
        }
    }

    // Reads endtimes from a file, and adds the end times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    public void readEndTimes(String endTimeFile){
        //TODO
    }

    // Reads endtimes from a file, and adds the end times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    public void readNames(String nameFile){
        //TODO
    }

    private List<String> readFile(String path) {
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
