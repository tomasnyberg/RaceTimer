package result.config;

public class Varv {
  private int minimumLaps;
  private String minimumTime;
  private boolean massStart;
  private String timeForMassStart;

  public Varv() {
    super();
  }

  public Varv(int minimumLaps, String minimumTime) {
    this.minimumLaps = minimumLaps;
    this.minimumTime = minimumTime;
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

}
