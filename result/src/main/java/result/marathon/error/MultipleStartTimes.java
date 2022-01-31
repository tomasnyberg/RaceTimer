package result.marathon.error;

import result.marathon.MarathonResult;

public class MultipleStartTimes extends MarathonDecorator {

  public MultipleStartTimes(MarathonResult result) {
    super(result, "Flera starttider?");
    errors.add(ERROR_STRING + " " + result.getStart());
  }
}
