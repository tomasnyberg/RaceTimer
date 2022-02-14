package result.marathon;

import result.AbstractDriver;
import result.config.Config;

import java.time.LocalTime;

public class MarathonDriver2 extends AbstractDriver {

    public MarathonDriver2(String driverNumber, Config config) {
        super(driverNumber, config);
    }

    @Override
    protected String getErrors() {
        StringBuilder sb =  new StringBuilder();

        if (startTimes.size() > 1){
            sb.append(multipleStartTimes);
            for (int i = 1; i<startTimes.size(); ++i){
                sb.append(" ");
                sb.append(startTimes.get(i));
            }
            sb.append(" ");
        }

        if (endTimes.size() > 1){
            sb.append(multipleEndTimes);
            for (int i = 1; i < endTimes.size(); ++i){
                sb.append(" ");
                sb.append(endTimes.get(i));
            }
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return "";
    }
}
