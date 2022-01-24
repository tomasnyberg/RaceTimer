package result.marathon;

import result.Driver;
import result.Result;
import result.ResultFormatter;
import util.FileWriter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Marathon implementation of the Driver class.
 */
public class MarathonResultExporter {

    private static ResultFormatter formatter = new MarathonFormatter();
    public static void export(String fileName, List<MarathonResult> results) throws java.io.IOException
    {
        FileWriter.dump(fileName, toStringList(results));
    }

    private static List<String> toStringList(List<MarathonResult> results)
    {
        return results
                      .stream()
                      .map(result -> formatter.formatDriver(result))
                      .collect(Collectors.toList());
    }

}
