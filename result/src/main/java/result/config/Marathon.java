package result.config;

/**
 * Config values for marathon race
 */
public class Marathon {
    private String minimumTime;
    private String startTimesFile;
    private String goalTimesFile;

    public Marathon() {
      super();
    }

    public Marathon(String minimumTime, String startTimesFile, String goalTimesFile) {
      this.minimumTime = minimumTime;
      this.startTimesFile = startTimesFile;
      this.goalTimesFile = goalTimesFile;
    }

    public String getGoalTimesFile() {
      return goalTimesFile;
    }

    public void setGoalTimesFile(String goalTimesFile) {
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