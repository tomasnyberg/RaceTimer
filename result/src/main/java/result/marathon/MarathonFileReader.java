package result.marathon;

import java.util.List;

import result.DriverEntry;
import result.TimeEntry;
import util.AbstractFileReader;
import util.NameFileReader;

import java.util.ArrayList;

public class MarathonFileReader extends AbstractFileReader {

  private static List<TimeEntry> generateTimeEntries(List<String> lines) {
    List<TimeEntry> result = new ArrayList<>();
    for (String s : lines) {
      result.add(generateTimeEntry(s));
    }
    return result;
  }

  public static List<MarathonResult> result(
      String nameFile, String startTimeFile, String endTimeFile, String minimumTime) {
    List<DriverEntry> driverEntries = null;
    List<TimeEntry> startTimeEntries = null;
    List<TimeEntry> endTimeEntries = null;

    if (readFile(nameFile) != null) {
      driverEntries = NameFileReader.result(nameFile);
    } else {
      System.out.println("Name file does not exist!");
      System.exit(0);
    }
    if (readFile(startTimeFile) != null) {
      startTimeEntries = generateTimeEntries(readFile(startTimeFile));
    } else {
      System.out.println("Start time file does not exist!");
      System.exit(0);
    }
    if (readFile(endTimeFile) != null) {
      endTimeEntries = generateTimeEntries(readFile(endTimeFile));
    } else {
      System.out.println("End time file does not exist!");
      System.exit(0);
    }

    MarathonMatcher mm = new MarathonMatcher(minimumTime);
    mm.addStartTimes(startTimeEntries);
    mm.addEndTimes(endTimeEntries);
    mm.addDrivers(driverEntries);
    return mm.result();
  }
}
