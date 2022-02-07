package result.config;

public class Varv {
    private int minimumLaps;
    private String minimumTime;

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
  }
