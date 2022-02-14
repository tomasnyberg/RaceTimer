package result.config;

import java.util.List;

public class Lap {
  private String raceEndTime;
  private boolean massStart;
  private String minimumTime;
  private String timeForMassStart;
  private String startTimesFile;
  private List<String> endTimesFiles;

  public Lap() {
    super();
  }

  public Lap(
      String raceEndTime,
      boolean massStart,
      String minimumTime,
      String timeForMassStart,
      String startTimesFile,
      List<String> endTimesFiles) {
    this.raceEndTime = raceEndTime;
    this.massStart = massStart;
    this.minimumTime = minimumTime;
    this.timeForMassStart = timeForMassStart;
    this.startTimesFile = startTimesFile;
    this.endTimesFiles = endTimesFiles;
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

  public List<String> getEndTimesFiles() {
    return endTimesFiles;
  }

  public void setEndTimesFiles(List<String> endTimesFiles) {
    this.endTimesFiles = endTimesFiles;
  }

  public String getStartTimesFile() {
    return startTimesFile;
  }

  public void setStartTimesFile(String startTimesFile) {
    this.startTimesFile = startTimesFile;
  }
}
