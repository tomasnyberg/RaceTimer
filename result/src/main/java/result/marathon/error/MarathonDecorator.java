package result.marathon.error;

import result.marathon.MarathonResult;

import java.util.ArrayList;
import java.util.List;

/** Decorator pattern to decorate marathon results with error messages. */
public abstract class MarathonDecorator implements MarathonResult {

  public static final String MISSING_TIME = "--:--:--";
  public static final String MULTIPLE_START_TIMES = "Flera starttider?";
  public static final String MULTIPLE_END_TIMES = "Flera måltider?";
  public static final String IMPOSSIBLE_TIME = "Omöjlig Totaltid?";

  public List<String> errors;

  private MarathonResult result;

  public MarathonDecorator(MarathonResult result) {
    this.result = result;
    errors = new ArrayList<>();
  }

  @Override
  public String getNumber() {
    return result.getNumber();
  }

  @Override
  public String getName() {
    return result.getName();
  }

  @Override
  public String getStart() {
    return result.getStart();
  }

  @Override
  public String getEnd() {
    return result.getEnd();
  }

  @Override
  public String getTotal() {
    return result.getTotal();
  }

  @Override
  public List<String> getErrors() {
    return errors;
  }
}
