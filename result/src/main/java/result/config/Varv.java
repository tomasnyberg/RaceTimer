package result.config;

import java.util.List;

public class Varv {
  private int minimumLaps;
  private String minimumTime;
  private boolean massStart;
  private String timeForMassStart;
  private String startTimesFile;
  private List<String> endTimesFiles;

  public Varv() {
    super();
  }

  public Varv(
      boolean massStart,
      String minimumTime,
      String timeForMassStart,
      String startTimesFile,
      List<String> endTimesFiles) {
    this.massStart = massStart;
    this.minimumTime = minimumTime;
    this.timeForMassStart = timeForMassStart;
    this.startTimesFile = startTimesFile;
    this.endTimesFiles = endTimesFiles;
  }

  public int getMinimumLaps() {
    return minimumLaps;
  }

  public void setMinimumLaps(int minimumLaps) {
    this.minimumLaps = minimumLaps;
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
