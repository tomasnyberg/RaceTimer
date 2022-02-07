package result.marathon.error;

import result.marathon.MarathonDriver;
import result.marathon.MarathonResult;
import util.TimeUtils;

public class MultipleEndTimes extends MarathonDecorator {

  public MultipleEndTimes(MarathonResult result, MarathonDriver driver) {
    super(result, "Flera måltider?");
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i < driver.getEndTimes().size(); ++i) {
      String time = TimeUtils.formatTime(driver.getEndTimes().get(i));
      builder.append(" " + time);
    }
    errors.add(ERROR_STRING + builder.toString());
  }
}
