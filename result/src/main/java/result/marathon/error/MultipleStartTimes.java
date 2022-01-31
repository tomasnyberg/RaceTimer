package result.marathon.error;

import result.marathon.MarathonDriver;
import result.marathon.MarathonResult;
import util.TimeUtils;

public class MultipleStartTimes extends MarathonDecorator {

  public MultipleStartTimes(MarathonResult result, MarathonDriver driver) {
    super(result, "Flera starttider?");
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i < driver.getStartTimes().size(); ++i) {
      String time = TimeUtils.formatTime(driver.getStartTimes().get(i));
      builder.append(" " + time);
    }
    errors.add(ERROR_STRING + builder.toString());
  }
}
