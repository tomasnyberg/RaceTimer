package result.config;

import java.util.List;

/**
 * Config values specific for lap races
 */
public class Lap {
  private String raceEndTime;
  private boolean massStart;
  private String minimumTime;
  private String timeForMassStart;
  private String startTimesFile;
  private List<String> goalTimesFiles;

  public Lap() {
    super();
  }

  public Lap(
      String raceEndTime,
      boolean massStart,
      String minimumTime,
      String timeForMassStart,
      String startTimesFile,
      List<String> goalTimesFiles) {
    this.raceEndTime = raceEndTime;
    this.massStart = massStart;
    this.minimumTime = minimumTime;
    this.timeForMassStart = timeForMassStart;
    this.startTimesFile = startTimesFile;
    this.goalTimesFiles = goalTimesFiles;
  }

  public String getRaceEndTime() {
    return raceEndTime;
  }

  public void setRaceEndTime(String raceEndTime) {
    this.raceEndTime = raceEndTime;
  }

  public String getMinimumTime() {
    return minimumTime;
  }

  public void setMinimumTime(String minimumTime) {
    this.minimumTime = minimumTime;
  }

  public boolean getMassStart() {
    return massStart;
  }

  public void setMassStart(boolean massStart) {
    this.massStart = massStart;
  }

  public String getTimeForMassStart() {
    return timeForMassStart;
  }

  public void setTimeForMassStart(String timeForMassStart) {
    this.timeForMassStart = timeForMassStart;
  }

  public List<String> getGoalTimesFiles() {
    return goalTimesFiles;
  }

  public void setGoalTimesFiles(List<String> goalTimesFiles) {
    this.goalTimesFiles = goalTimesFiles;
  }

  public String getStartTimesFile() {
    return startTimesFile;
  }

  public void setStartTimesFile(String startTimesFile) {
    this.startTimesFile = startTimesFile;
  }
}
