package result;

import result.config.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResult {
  public List<AbstractDriver> drivers;
  protected Config config;

  public AbstractResult(Config config) {
    this.drivers = new ArrayList<>();
    this.config = config;
  }

  public abstract void generateResult();

  private String[] formatChecker(String string, String fileName) {
    String[] split = string.split(";");
    if (split.length != 2) {
      throw new RuntimeException(fileName + " has the wrong format!" + "\n" + string);
    }
    split[0] = split[0].trim();
    split[1] = split[1].trim();
    return split;
  }

  // both read start times and end times read the times, so we have a method for
  // the common things
  // returns something that the other methods can use to fill in the info for the
  // drivers
  protected void readTimes(String filePath, boolean start) {
    List<String> lines = readFile(filePath);
    for (String line : lines) {
      String[] split = formatChecker(line, filePath);
      String time = split[1];
      String driverNumber = split[0];
      boolean found = false;
      for (int i = 0; i < drivers.size(); i++) {
        if (drivers.get(i).getDriverNumber().equals(driverNumber)) {
          if (start) {
            drivers.get(i).addStartTime(time);
          } else {
            drivers.get(i).addGoalTime(time);
          }
          found = true;
          break;
        }
      }
      if (!found) {
        AbstractDriver driver = newDriver(driverNumber, config);
        if (start) {
          driver.addStartTime(time);
        } else {
          driver.addGoalTime(time);
        }
        drivers.add(driver);
      }
    }
  }

  // Reads starttimes from a starttimefile, and sets the start times for the
  // respective driver
  // number
  // If we have not seen this drivernumber so far, we create a new driver
  public abstract void readStartTimes();

  // Reads endtimes from a file, and adds the end times for the respective driver
  // number
  // If we have not seen this drivernumber so far, we create a new driver
  public abstract void readEndTimes();

  // Reads names from a file
  // If we have not seen this drivernumber so far, we create a new driver
  public void readNames() {
    List<String> lines = readFile(config.getNameFile());
    for (int i = 1; i < lines.size(); i++) {
      String[] split = formatChecker(lines.get(i), config.getNameFile());
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
        AbstractDriver driver = newDriver(driverNumber, config);
        driver.setName(name);
        drivers.add(driver);
      }
    }
  }

  protected abstract AbstractDriver newDriver(String driverNumber, Config config);

  protected List<String> readFile(String path) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(path));
      List<String> list = new ArrayList<>();
      String line = reader.readLine();
      while (line != null) {
        list.add(line);
        line = reader.readLine();
      }
      reader.close();
      return list;
    } catch (Exception e) {
      System.out.println("Could not find " + path);
      return null;
    }
  }

}
