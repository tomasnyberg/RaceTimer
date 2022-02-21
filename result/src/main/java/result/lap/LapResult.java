package result.lap;

import result.AbstractDriver;
import result.AbstractResult;
import result.config.Config;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import util.FileWriter;

public class LapResult extends AbstractResult {

  public LapResult(Config config) {
    super(config);
  }

  // The only public method visible, reads in all the files and generates the
  // result txt file.
  // Readds from args if they exist for specifying where files are, otherwise from
  // config
  // Takes in a config class where we get the file paths etc.
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
    /*
        if (config.isSorting()) {
            topLine = "Rank; StartNr; Namn; Totaltid; Start; Mål";
            Collections.sort(drivers);
            dumpList.add(topLine);
            for (int i = 0; i < drivers.size(); i++) {
                String rank = Integer.toString(i + 1);
                dumpList.add(rank + "; " + drivers.get(i).toString());
            }
        } else {
            topLine = "StartNr; Namn; Totaltid; Start; Mål";
            dumpList.add(topLine);
            for (int i = 0; i < drivers.size(); i++) {
                dumpList.add(drivers.get(i).toString());
            }
        }
    */
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

  protected AbstractDriver newDriver(String driverNumber, Config config) {
    return new LapDriver(driverNumber, config);
  }

  public void readStartTimes() {
    readTimes(config.getLap().getStartTimesFile(), true);
  }

  // Reads endtimes from a file, and adds the end times for the respective driver
  // number
  // If we have not seen this drivernumber so far, we create a new driver
  public void readEndTimes() {
    for (String goalFile : config.getLap().getGoalTimesFiles()) {
      readTimes(goalFile, false);
    }
  }

}
