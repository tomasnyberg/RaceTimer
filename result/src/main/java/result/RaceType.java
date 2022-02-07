package result;

public enum RaceType {
  MARATON("maraton"),
  VARV("varv");

  public final String label;

  private RaceType(String label) {
    this.label = label;
  }
}
