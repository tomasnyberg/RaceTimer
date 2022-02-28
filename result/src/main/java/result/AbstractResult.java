package result;

import result.config.Config;
import util.OSString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for generating the result of a race
 */
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

  /**
   * Method for reading both start and end times from a text file
   * If we have not seen the driver number before, we create a new driver
   * @param filePath file path to the input text file
   * @param start true if the times to be read are start times, false if they are goal times
   */
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

  public abstract void readStartTimes();

  public abstract void readEndTimes();

  /**
   * Read names from a file
   * If we have not seen a driver number so far, we create a new driver
   */
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

  /**
   * General method reading files
   * @param path file path to the text file
   * @return a list of strings containing the lines in the text file
   */
  protected List<String> readFile(String path) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
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
