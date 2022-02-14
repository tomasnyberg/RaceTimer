package result.marathon;

import result.AbstractDriver;
import result.config.Config;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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
//        List<String> columns;
//        if (config.isSorting()) {
//            columns = new ArrayList<>(
//                    Arrays.asList(
//                            driverNumber, name, getTotalTime()));
//        } else {
//
//        }
//
//        columns.add(getStartTime());
//        columns.add(getGoalTime());
//        columns.add(getErrors());
//
//        StringBuilder sb = new StringBuilder();
//        for (var column : columns) {
//            sb.append(column).append(SEP).append(' ');
//        }
//
//        // Remove the last separator and space
//        String result = sb.substring(0, sb.length() - 2);
//        result = result.endsWith("; ") ? result.substring(0, result.length() - 2) : result;
//        return result;
        return "";
    }
}
