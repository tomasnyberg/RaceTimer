package result.marathon.error;

import result.marathon.MarathonResult;

public class ImpossibleTotalTime extends MarathonDecorator {

  public ImpossibleTotalTime(MarathonResult result) {
    super(result);
    errors.add(IMPOSSIBLE_TIME);
  }
}
