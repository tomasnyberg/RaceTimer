package result.marathon.error;

import result.marathon.MarathonResult;

public class MultipleEndTimes extends MarathonDecorator {

  public MultipleEndTimes(MarathonResult result) {
    super(result);
    errors.add(MULTIPLE_START_TIMES + " " + result.getStart());
  }
}
