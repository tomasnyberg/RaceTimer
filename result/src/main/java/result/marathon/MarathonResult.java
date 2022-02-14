package result.marathon;

import result.AbstractDriver;
import result.AbstractResult;
import result.config.Config;
import util.FileWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MarathonResult extends AbstractResult {

    public MarathonResult(Config config) {
        super(config);
    }

    @Override
    public void generateResult() {
        String topLine;
        List<String> dumpList = new ArrayList<>();
        dumpList.add(config.getTitle());
        dumpList.add("");

        readStartTimes();
        readEndTimes();
        readNames();

        if(config.isSorting()){
            topLine = "Rank; StartNr; Namn; Totaltid; Start; Mål";
            Collections.sort(drivers);
            dumpList.add(topLine);
            for(int i = 0; i < drivers.size(); i++){
                String rank = Integer.toString(i+1);
                dumpList.add(rank + "; " + drivers.get(i).toString());
            }
        } else {
            topLine = "StartNr; Namn; Totaltid; Start; Mål";
            dumpList.add(topLine);
            for(int i = 0; i < drivers.size(); i++){
                dumpList.add(drivers.get(i).toString());
            }
        }
        dumpList.add("");
        dumpList.add(config.getFooter());
        try {
            FileWriter.dump(config, dumpList);
        } catch (Exception e){
            //
        }
    }

    protected AbstractDriver newDriver(String driverNumber, Config config) {
        return new MarathonDriver(driverNumber, config);
    }

    public void readStartTimes() {
        readTimes(config.getMarathon().getStartTimesFile(), true);
    }

    // Reads endtimes from a file, and adds the end times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    public void readEndTimes() {
        readTimes(config.getMarathon().getGoalTimesFile(), false);
    }

}
