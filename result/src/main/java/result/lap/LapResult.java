package result.lap;

import result.AbstractDriver;
import result.AbstractResult;
import result.config.Config;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import util.FileWriter;

/**
 * Class for generating a result for a lap race
 */
public class LapResult extends AbstractResult {
  /**
   *
   * @param config a config file
   */
  public LapResult(Config config) {
    super(config);
  }

  /**
   * Method for generating the result. Generates a result.txt file
   */
  public void generateResult() {
    readEndTimes();
    if (config.getLap().getMassStart()) {
      for (AbstractDriver d : drivers) {
        d.addStartTime(config.getLap().getTimeForMassStart());
      }
    } else {
      readStartTimes();
    }
    readNames();
    int max = 0;
    for (AbstractDriver d : drivers) {
      max = Math.max(((LapDriver) d).getAmountOfLaps(), max);
    }
    for (AbstractDriver d : drivers) {
      ((LapDriver) d).setMaxLaps(max);
    }
    List<String> dumpList = new ArrayList<>();
    dumpList.add(config.getTitle());
    dumpList.add("");
    String topLine = "StartNr; Namn; #Varv; Totaltid; ";
    for (int i = 1; i <= max; i++) {
      topLine += "Varv" + i + "; ";
    }
    topLine += "Start; ";
    for (int i = 1; i < max; i++) {
      topLine += "Varvning" + i + "; ";
    }
    topLine += "Mål";
    if (config.isSorting()) {
      topLine = "Rank; " + topLine;
      dumpList.add(topLine);
      Collections.sort(drivers);
      for (int i = 0; i < drivers.size(); i++) {
        dumpList.add(Integer.toString(i + 1) + "; " + drivers.get(i).toString());
      }
    } else {
      dumpList.add(topLine);
      for (AbstractDriver driver : drivers) {
        dumpList.add(driver.toString());
      }
    }
    dumpList.add("");
    dumpList.add(config.getFooter());
    try {
      FileWriter.dump(config, dumpList);
    } catch (Exception e) {
      //
    }

  }

  /**
   * @param driverNumber String with a driver number
   * @param config a config file
   * @return Generates a Lap driver
   */
  protected AbstractDriver newDriver(String driverNumber, Config config) {
    return new LapDriver(driverNumber, config);
  }

  /**
   * Method for reading start times from a text file
   */
  public void readStartTimes() {
    readTimes(config.getLap().getStartTimesFile(), true);
  }

  /**
   * Reads end times from a text file and adds the end times for the respective driver number
   */
  public void readEndTimes() {
    for (String goalFile : config.getLap().getGoalTimesFiles()) {
      readTimes(goalFile, false);
    }
  }
}
