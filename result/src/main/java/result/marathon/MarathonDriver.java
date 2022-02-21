package result.marathon;

import result.AbstractDriver;
import result.config.Config;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for representing one driver in a marathon race
 */

public class MarathonDriver extends AbstractDriver {

  protected static final String impossibleTotalTime = "Omöjlig Totaltid?";
  /**
   * @param driverNumber String with a drivernumber
   * @param config  a config file
   */
  public MarathonDriver(String driverNumber, Config config) {
    super(driverNumber, config);
  }

  /**
   *
   * @return String of an error
   */
  @Override
  public String getErrors() {
    List<String> errors = new ArrayList<>();

    // Multiple start times
    if (startTimes.size() > 1) {
      String temp = "";
      temp += multipleStartTimes;

      for (int i = 1; i < startTimes.size(); i++) {
        temp += " ";
        temp += startTimes.get(i);
      }

      errors.add(temp);
    }

    // Multiple goal times
    if (goalTimes.size() > 1) {
      String temp = "";
      temp += multipleGoalTimes;

      for (int i = 1; i < goalTimes.size(); i++) {
        temp += " ";
        temp += goalTimes.get(i);
      }

      errors.add(temp);
    }

    // Impossible total time
    String totalTime = getTotalTime();
    LocalTime minimumTime = LocalTime.parse(config.getMarathon().getMinimumTime());

    if (!missingStartOrGoal()) { // if we have start and end
      String temp = "";
      if (goalTimeBeforeStartTime()) { // if goal time before start time
        temp += impossibleTotalTime;
        errors.add(temp);
      } else if (LocalTime.parse(totalTime).isBefore(minimumTime)) {
        temp = "";
        temp += impossibleTotalTime;
        errors.add(temp);
      }
    }

    StringBuilder errorBuilder = new StringBuilder();

    for (int i = 0; i < errors.size(); i++) {
      String sep = "";
      if (i != errors.size() - 1) sep = ERROR_SEP + " ";
      errorBuilder.append(errors.get(i) + sep);
    }

    return errorBuilder.toString().trim();
  }

  /**
   *
   * @return True if driver is missing start time or goal time. False otherwise
   */
  private boolean missingStartOrGoal() {
    return getStartTime().equals(missingStartTime) || getGoalTime().equals(missingGoalTime);
  }


  /**
   * The main part of this class. Creates the result row for the result file corresponding to this driver
   * @return A result row for a driver
   */
  @Override
  public String toString() {
    List<String> columns = new ArrayList<>();
    columns.add(driverNumber);
    columns.add(name);
    columns.add(getTotalTime());
    columns.add(getStartTime());
    columns.add(getGoalTime());

    if (!config.isSorting()) {
      columns.add(getErrors());
    }

    StringBuilder sb = new StringBuilder();
    for (String column : columns) {
      sb.append(column).append(SEP).append(' ');
    }

    // Remove the last separator and space
    String result = sb.substring(0, sb.length() - 2);
    result = result.endsWith("; ") ? result.substring(0, result.length() - 2) : result;

    if (config.isSorting()) {
      result = result.replace(missingStartTime, invalidTime).replace(missingGoalTime, invalidTime);
    }

    return result;
  }

  /**
   *
   * @param other an AbstractDriver
   * @return a comparison of total times and errors between two Marathon drivers
   */
  @Override
  public int compareTo(AbstractDriver other) {
    if (isErrors() && !other.isErrors()) return 1;
    else if (!isErrors() && other.isErrors()) return -1;
    else if (isErrors() && other.isErrors()) return 0;
    else return getTotalTime().compareTo(other.getTotalTime());
  }
}
