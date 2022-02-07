package result.config;

import java.util.List;

public class Varv {
  private boolean massStart;
  private String minimumTime;
  private String startTimesFile;
  private List<String> endTimesFiles;

  public Varv() {
    super();
  }

  public Varv(
      boolean massStart, String minimumTime, String startTimesFile, List<String> endTimesFiles) {
    this.massStart = massStart;
    this.minimumTime = minimumTime;
    this.startTimesFile = startTimesFile;
    this.endTimesFiles = endTimesFiles;
  }

  public boolean getMassStart() {
    return massStart;
  }

  public void setMassStart(boolean massStart) {
    this.massStart = massStart;
  }

  public String getMinimumTime() {
    return minimumTime;
  }

  public void setMinimumTime(String minimumTime) {
    this.minimumTime = minimumTime;
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
