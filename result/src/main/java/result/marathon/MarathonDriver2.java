package result.marathon;

import result.AbstractDriver;
import result.config.Config;

public class MarathonDriver2 extends AbstractDriver {

    public MarathonDriver2(String driverNumber, Config config) {
        super(driverNumber, config);
    }

    @Override
    protected String getErrors() {
        return null;
    }
}
