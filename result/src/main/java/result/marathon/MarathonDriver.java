package result.marathon;

import result.AbstractDriver;
import result.config.Config;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MarathonDriver extends AbstractDriver {

    protected static final String impossibleTotalTime = "Omöjlig totaltid?";

    public MarathonDriver(String driverNumber, Config config) {
        super(driverNumber, config);
    }

    @Override
    public String getErrors() {
        List<String> errors = new ArrayList<>();

        // Multiple start times
        if (startTimes.size() > 1){
            String temp = "";
            temp += multipleStartTimes;

            for (int i = 1; i < startTimes.size(); i++) {
                temp += " ";
                temp += startTimes.get(i);
            }

            errors.add(temp);
        }

        // Multiple goal times
        if (goalTimes.size() > 1) {
            String temp = "";
            temp += multipleGoalTimes;

            for (int i = 1; i < goalTimes.size(); i++) {
                temp += " ";
                temp += goalTimes.get(i);
            }

            errors.add(temp);
        }

        // Impossible total time
        String totalTime = getTotalTime();
        LocalTime minimumTime = LocalTime.parse(config.getMarathon().getMinimumTime());

        if(!missingStartOrGoal()) { // if we have start and end
            String temp = "";
            if (goalTimeBeforeStartTime()) { // if goal time before start time
                temp += impossibleTotalTime;
                errors.add(temp);
            } else if (LocalTime.parse(totalTime).isBefore(minimumTime)) {
                temp = "";
                temp += impossibleTotalTime;
                errors.add(temp);
            }
        }

        StringBuilder errorBuilder = new StringBuilder();

        for(int i = 0; i < errors.size(); i++) {
            String sep = "";
            if(i != errors.size()-1) sep = ERROR_SEP + " ";
            errorBuilder.append(errors.get(i) + sep);
        }

        return errorBuilder.toString().trim();
    }

    private boolean missingStartOrGoal() {
        return getStartTime().equals(missingStartTime) || getGoalTime().equals(missingGoalTime);
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

        if (config.isSorting()) {
            result = result.replace(missingStartTime, invalidTime).replace(missingGoalTime, invalidTime);
        }

        return result;
    }

    @Override
    public int compareTo(AbstractDriver other) {
        if(isErrors() && !other.isErrors()) return 1;
        else if(!isErrors() && other.isErrors()) return -1;
        else if(isErrors() && other.isErrors()) return 0;
        else return getTotalTime().compareTo(other.getTotalTime());
    }
}
