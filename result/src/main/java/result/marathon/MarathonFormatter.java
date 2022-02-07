package result.marathon;

import result.ResultFormatter;

/**
 * Formats marathon results.
 */
public class MarathonFormatter implements ResultFormatter<MarathonResult> {

    @Override
    public String formatDriver(MarathonResult driverResult, boolean shouldSort) {
        StringBuilder sb = new StringBuilder();

        // start number
        sb.append(driverResult.getNumber()).append(SEP).append(" ");

        // driver name
        sb.append(driverResult.getName()).append(SEP).append(" ");

        // total time
        sb.append(driverResult.getTotal()).append(SEP).append(" ");

        // start time
        sb.append(driverResult.getStart()).append(SEP).append(" ");

        // end time
        sb.append(driverResult.getEnd());

        // errors in the extra error column
        if (!shouldSort) {
            boolean addComma = false;
            if (driverResult.getErrors().size() > 0) sb.append(SEP).append(" ");
            else sb.append(" ");
            for (String e : driverResult.getErrors()) {
                if (addComma) {
                    sb.append(", ");
                } else {
                    addComma = true;
                }
                sb.append(e);
            }
        }

        return shouldSort ? sb.toString().replace("Start?", "--:--:--").replace("Slut?", "--:--:--") : sb.toString();
    }

}
