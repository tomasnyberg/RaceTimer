package result.marathon;

import result.AbstractDriver;
import result.AbstractResult;
import result.config.Config;
import result.lap.LapDriver;
import util.FileWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MarathonResult2 extends AbstractResult {

    public MarathonResult2(Config config) {
        super(config);
    }

    @Override
    public void generateResult() {
        String topLine;

        readStartTimes();
        readEndTimes();
        readNames();

        if(config.isSorting()){
            topLine = "Rank; StartNr; Namn; Totaltid; Start; Mål";
            Collections.sort(drivers);
        }else {
            topLine = "StartNr; Namn; Totaltid; Start; Mål";
        }

        List<String> dumpList = new ArrayList<>();
        dumpList.add(config.getTitle());
        dumpList.add("");
        dumpList.add(topLine);
        for(AbstractDriver d: drivers){
            dumpList.add(d.toString());
        }
        dumpList.add("");
        dumpList.add(config.getFooter());
        try {
            FileWriter.dump(config, dumpList);
        } catch (Exception e){
            //
        }
    }

    @Override
    protected void readTimes(String filePath, boolean start) {

    }

    @Override
    protected void readStartTimes() {

    }

    @Override
    protected void readEndTimes() {

    }

    protected AbstractDriver newDriver(String driverNumber, Config config) {
        return new MarathonDriver2(driverNumber, config);
    }

}
