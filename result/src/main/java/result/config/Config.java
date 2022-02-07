package result.config;

public class Config {
  // Common values
  private String type;
  private String title;
  private String footer;
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
      String footer,
      boolean sorting,
      String resultFile,
      String nameFile,
      Maraton maraton,
      Varv varv) {
    this.type = type;
    this.title = title;
    this.footer = footer;
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
