package result.Varvlopp;

import result.config.Config;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import util.FileWriter;

public class VarvloppResult{
    public List<VarvloppDriver> drivers;
    private Config config;


  public VarvloppResult(Config config) {
    this.drivers = new ArrayList<>();
    this.config = config;
  }

  // The only public method visible, reads in all the files and generates the result txt file.
  // Readds from args if they exist for specifying where files are, otherwise from config
  // Takes in a config class (TODO) where we get the file paths etc.
  public void generateResult() {
      readEndTimes();
      if(config.getVarv().getMassStart()){
          for(VarvloppDriver d: drivers){
              d.addStartTime(config.getVarv().getTimeForMassStart());
          }
      } else {
          readStartTimes();
      }
      readNames();
      if(config.isSorting()){
          Collections.sort(drivers);
      }
      int max = 0;
      for(VarvloppDriver d: drivers){
          max = Math.max(d.getAmountOfLaps(), max);
      }
      for(VarvloppDriver d: drivers){
        d.setMaxLaps(max);
      }
      List<String> dumpList = new ArrayList<>();
      for(VarvloppDriver d: drivers){
        dumpList.add(d.toString());
      }
      try {
          FileWriter.dump(config, dumpList);
      } catch (Exception e){
          //
      }

  }

  // both read start times and end times read the times, so we have a method for the common things
  // returns something that the other methods can use to fill in the info for the drivers
  private void readTimes(String filePath, boolean start) {
    List<String> lines = readFile(filePath);
    for (String line : lines) {
      String[] split = line.split("; ");
      String time = split[1];
      String driverNumber = split[0];
      boolean found = false;
      for (int i = 0; i < drivers.size(); i++) {
        if (drivers.get(i).getDriverNumber().equals(driverNumber)) {
          if (start) {
            drivers.get(i).addStartTime(time);
          } else {
            drivers.get(i).addEndTime(time);
          }
          found = true;
          break;
        }
      }
      if (!found) {
        VarvloppDriver driver = new VarvloppDriver(driverNumber, config);
        if (start) {
          driver.addStartTime(time);
        } else {
          driver.addEndTime(time);
        }
        drivers.add(driver);
      }
    }
  }

  // Reads starttimes from a starttimefile, and sets the start times for the respective driver
  // number
  // If we have not seen this drivernumber so far, we create a new driver
  public void readStartTimes() {
    readTimes(config.getVarv().getStartTimesFile(), true);
  }

  // Reads endtimes from a file, and adds the end times for the respective driver number
  // If we have not seen this drivernumber so far, we create a new driver
  public void readEndTimes() {
    for (String endFile : config.getVarv().getEndTimesFiles()) {
      readTimes(endFile, false);
    }
  }

  // Reads endtimes from a file, and adds the end times for the respective driver number
  // If we have not seen this drivernumber so far, we create a new driver
  public void readNames() {
    List<String> lines = readFile(config.getNameFile());
    for (int i = 1; i < lines.size(); i++) {
      String[] split = lines.get(i).split("; ");
      String driverNumber = split[0];
      String name = split[1];
      boolean found = false;
      for (int j = 0; j < drivers.size(); j++) {
        if (drivers.get(j).getDriverNumber().equals(driverNumber)) {
          found = true;
          drivers.get(j).setName(name);
          break;
        }
      }
      if (!found) {
        VarvloppDriver driver = new VarvloppDriver(driverNumber, config);
        driver.setName(name);
        drivers.add(driver);
      }
    }
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
