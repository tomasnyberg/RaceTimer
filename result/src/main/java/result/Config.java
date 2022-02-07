package result;

public class Config {
  // Common values
  private String type;
  private String title;
  private boolean sorting;
  private String resultFile;
  private String nameFile;
  private Maraton maraton;
  private Varv varv;

  public Config() {
    super();
  }

  public Config(
      String type,
      String title,
      boolean sorting,
      String resultFile,
      String nameFile,
      Maraton maraton,
      Varv varv) {
    this.type = type;
    this.title = title;
    this.sorting = sorting;
    this.resultFile = resultFile;
    this.nameFile = nameFile;
    this.maraton = maraton;
    this.varv = varv;
  }

  public Varv getVarv() {
    return varv;
  }

  public void setVarv(Varv varv) {
    this.varv = varv;
  }

  public Maraton getMaraton() {
    return maraton;
  }

  public void setMaraton(Maraton maraton) {
    this.maraton = maraton;
  }

  public boolean isSorting() {
    return sorting;
  }

  public void setSorting(boolean sorting) {
    this.sorting = sorting;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getNameFile() {
    return nameFile;
  }

  public void setNameFile(String nameFile) {
    this.nameFile = nameFile;
  }

  public String getResultFile() {
    return resultFile;
  }

  public void setResultFile(String resultFile) {
    this.resultFile = resultFile;
  }

  // Marathon
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

  public class Varv {
    private int minimumLaps;

    public Varv() {
      super();
    }

    public Varv(int minimumLaps) {
      this.minimumLaps = minimumLaps;
    }

    public int getMinimumLaps() {
      return minimumLaps;
    }

    public void setMinimumLaps(int minimumLaps) {
      this.minimumLaps = minimumLaps;
    }
  }
}
