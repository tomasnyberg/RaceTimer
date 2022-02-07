package result.config;

public class Maraton {
    private String minimumTime;
    private String startTimesFile;
    private String endTimesFile;

    public Maraton() {
      super();
    }

    public Maraton(String minimumTime, String startTimesFile, String endTimesFile) {
      this.minimumTime = minimumTime;
      this.startTimesFile = startTimesFile;
      this.endTimesFile = endTimesFile;
    }

    public String getEndTimesFile() {
      return endTimesFile;
    }

    public void setEndTimesFile(String endTimesFile) {
      this.endTimesFile = endTimesFile;
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