package result.config;

/**
 * A class representing the common values between different types of races
 * These values are collected from the config.yaml file automatically
 */
public class Config {
  // Common values
  private String type;
  private String title;
  private String footer;
  private boolean sorting;
  private String resultFile;
  private String nameFile;
  private Marathon marathon;
  private Lap lap;

  public Config() {
    super();
  }

  public Config(
      String type,
      String title,
      String footer,
      boolean sorting,
      String resultFile,
      String nameFile,
      Marathon marathon,
      Lap lap) {
    this.type = type;
    this.title = title;
    this.footer = footer;
    this.sorting = sorting;
    this.resultFile = resultFile;
    this.nameFile = nameFile;
    this.marathon = marathon;
    this.lap = lap;
  }

  public Lap getLap() {
    return lap;
  }

  public void setLap(Lap lap) {
    this.lap = lap;
  }

  public Marathon getMarathon() {
    return marathon;
  }

  public void setMarathon(Marathon marathon) {
    this.marathon = marathon;
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

  public void setFooter(String footer) {
    this.footer = footer;
  }

  public String getFooter() {
    return footer;
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
}
