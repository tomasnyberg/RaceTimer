package result.marathon;

import result.Driver;
import result.Result;
import result.ResultFormatter;
import util.FileWriter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Marathon implementation of the Driver class.
 */
public class MarathonResultExporter {

    public static String HEADER = "StartNr; Starttid; Måltid";

    private static ResultFormatter formatter = new MarathonFormatter();
    public static void export(String fileName, List<MarathonResult> results) throws java.io.IOException {
        FileWriter.dump(fileName, toStringList(results));
    }

    private static List<String> toStringList(List<MarathonResult> input) {
        List<String> results = new ArrayList<>();
        results.add(HEADER);
        results.addAll(input
                      .stream()
                      .map(result -> formatter.formatDriver(result))
                      .collect(Collectors.toList()));
        return results;
    }

}
