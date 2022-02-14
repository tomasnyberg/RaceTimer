package result.config;

public class Marathon {
    private String minimumTime;
    private String startTimesFile;
    private String goalTimesFile;

    public Marathon() {
      super();
    }

    public Marathon(String minimumTime, String startTimesFile, String endTimesFile) {
      this.minimumTime = minimumTime;
      this.startTimesFile = startTimesFile;
      this.goalTimesFile = goalTimesFile;
    }

    public String getEndTimesFile() {
      return goalTimesFile;
    }

    public void setEndTimesFile(String endTimesFile) {
      this.goalTimesFile = goalTimesFile;
    }

    public String getStartTimesFile() {
      return startTimesFile;
    }

    public void setStartTimesFile(String startTimesFile) {
      this.startTimesFile = startTimesFile;
    }

    public String getMinimumTime() {
      return minimumTime;
    }

    public void setMinimumTime(String minimumTime) {
      this.minimumTime = minimumTime;
    }
  }