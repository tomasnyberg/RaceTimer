package result.marathon;

import result.DriverEntry;
import result.Matcher;
import result.TimeEntry;
import result.marathon.error.ImpossibleTotalTime;
import result.marathon.error.MarathonDecorator;
import result.marathon.error.MissingEndTime;
import result.marathon.error.MissingStartTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MarathonMatcher extends Matcher<MarathonDriver, MarathonResult> {

  public MarathonMatcher(String minimumTime) {

    // Find missing start times
    errorFinders.add(
        (driver, result) -> driver.getStart() == null ? new MissingStartTime(result) : result);
    // Find missing end times
    errorFinders.add(
        (driver, result) -> driver.getEnd() == null ? new MissingEndTime(result) : result);

    // Find impossible total times
    errorFinders.add(
        (driver, result) ->
            !(result instanceof MarathonDecorator)
                    && LocalTime.parse(result.getTotal()).isBefore(LocalTime.parse(minimumTime))
                ? new ImpossibleTotalTime(result)
                : result);
  }

  public void addStartTimes(List<TimeEntry> newTimes) {
    for (TimeEntry entry : newTimes) {
      getDriver(entry.getNumber()).setStart(entry.getTime());
    }
  }

  public void addEndTimes(List<TimeEntry> newTimes) {
    for (TimeEntry entry : newTimes) {
      getDriver(entry.getNumber()).setEnd(entry.getTime());
    }
  }

  @Override
  public void addDrivers(List<DriverEntry> newDrivers) {
    for (DriverEntry entry : newDrivers) {
      getDriver(entry.getNumber()).setName(entry.getName());
    }
  }

  private MarathonDriver getDriver(String number) {
    MarathonDriver driver;
    if ((driver = drivers.get(number)) == null) {
      driver = new MarathonDriver(number);
      drivers.put(number, driver);
    }
    return driver;
  }

  @Override
  public List<MarathonResult> result() {
    List<MarathonResult> results = new ArrayList<>();
    for (MarathonDriver driver : drivers.values()) {
      MarathonResult result =
          new MarathonResultRow(
              driver.getStartNumber(), driver.getName(), driver.getStart(), driver.getEnd());
      result = findErrors(driver, result); // decorate result with errors
      results.add(result); // add decorated result to list
    }
    return results;
  }
}
