package result.marathon.error;

import result.marathon.MarathonResult;

public class MultipleEndTimes extends MarathonDecorator {

  public MultipleEndTimes(MarathonResult result) {
    super(result, "Flera måltider?");
    errors.add(ERROR_STRING + " " + result.getStart());
  }
}
