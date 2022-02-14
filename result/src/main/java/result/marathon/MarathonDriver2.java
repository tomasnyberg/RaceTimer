package result.marathon;

import result.AbstractDriver;
import result.config.Config;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MarathonDriver2 extends AbstractDriver {

    protected static final String impossibleTotalTime = "Omöjlig totaltid?";

    public MarathonDriver2(String driverNumber, Config config) {
        super(driverNumber, config);
    }

    @Override
    protected String getErrors() {
        StringBuilder sb =  new StringBuilder();

        // Multiple start times
        if (startTimes.size() > 1){
            sb.append(multipleStartTimes);
            for (int i = 1; i<startTimes.size(); ++i){
                sb.append(" ");
                sb.append(startTimes.get(i));
            }
            sb.append(" ");
        }

        // Multiple goal times
        if (goalTimes.size() > 1){
            sb.append(multipleGoalTimes);
            for (int i = 1; i < goalTimes.size(); ++i){
                sb.append(" ");
                sb.append(goalTimes.get(i));
            }
            sb.append(" ");
        }

        // Impossible total time
        String totalTime = getTotalTime();
        LocalTime minimumTime = LocalTime.parse(config.getMarathon().getMinimumTime());
        if (totalTime != invalidTime) {
            if(LocalTime.parse(totalTime).isBefore(minimumTime)) {
                sb.append(impossibleTotalTime);
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }

    @Override
    public String toString() {
        List<String> columns = new ArrayList<>();
        columns.add(driverNumber);
        columns.add(name);
        columns.add(getTotalTime());
        columns.add(getStartTime());
        columns.add(getGoalTime());

        if (!config.isSorting()) {
            columns.add(getErrors());
        }

        StringBuilder sb = new StringBuilder();
        for (String column : columns) {
            sb.append(column).append(SEP).append(' ');
        }

        // Remove the last separator and space
        String result = sb.substring(0, sb.length() - 2);
        result = result.endsWith("; ") ? result.substring(0, result.length() - 2) : result;
        return result;
    }

    @Override
    public int compareTo(AbstractDriver other) {
        if(getErrors().length() == 0 && getErrors().length() != 0) return 1;
        else if(getErrors().length() != 0 && getErrors().length() == 0) return -1;
        else if(getErrors().length() != 0 && getErrors().length() != 0) return 0;
        else return getTotalTime().compareTo(other.getTotalTime());

    }
}
