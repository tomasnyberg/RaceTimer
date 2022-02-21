package result.marathon;

import result.AbstractDriver;
import result.AbstractResult;
import result.config.Config;
import util.FileWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for generating a result for a marathon race
 */
public class MarathonResult extends AbstractResult {
    /**
     *
     * @param config a config file
     */
    public MarathonResult(Config config) {
        super(config);
    }

    /**
     * Method for generating the result. Generates a result.txt file
     */
    @Override
    public void generateResult() {
        String topLine;
        List<String> dumpList = new ArrayList<>();
        dumpList.add(config.getTitle());
        dumpList.add("");

        readNames();
        readStartTimes();
        readEndTimes();

        if (config.isSorting()) {
            topLine = "Rank; StartNr; Namn; Totaltid; Start; Mål";
            Collections.sort(drivers);
            dumpList.add(topLine);
            for (int i = 0; i < drivers.size(); i++) {
                String rank = Integer.toString(i + 1);
                dumpList.add(rank + "; " + drivers.get(i).toString());
            }
        } else {
            topLine = "StartNr; Namn; Totaltid; Start; Mål";
            dumpList.add(topLine);
            for (int i = 0; i < drivers.size(); i++) {
                dumpList.add(drivers.get(i).toString());
            }
        }
        dumpList.add("");
        dumpList.add(config.getFooter());
        try {
            FileWriter.dump(config, dumpList);
        } catch (Exception e) {
            //
        }
    }

    /**
     *
     * @param driverNumber String with a drivernumber
     * @param config    a config file
     * @return Generates a MarathonDriver
     */
    protected AbstractDriver newDriver(String driverNumber, Config config) {
        return new MarathonDriver(driverNumber, config);
    }

    /**
     * Method for reading start times from a text file
     */
    public void readStartTimes() {
        readTimes(config.getMarathon().getStartTimesFile(), true);
    }

    /**
     * Method for reading end times from a text file
     */
    public void readEndTimes() {
        readTimes(config.getMarathon().getGoalTimesFile(), false);
    }

}
