package result.marathon;


import result.ResultFormatter;
import util.FileWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Marathon implementation of the Driver class.
 */
public class MarathonResultExporter {

    public static String HEADER = "StartNr; Namn; Totaltid; Start; Mål";
    public static String SORTEDHEADER = "Rank; StartNr; Namn; Totaltid; Start; Mål";

    private static ResultFormatter formatter = new MarathonFormatter();
    public static void export(String fileName, List<MarathonResult> results, boolean shouldSort) throws java.io.IOException {
        FileWriter.dump(fileName, toStringList(results, shouldSort));
    }

    private static List<String> toStringList(List<MarathonResult> input, boolean shouldSort) {
        List<String> results = new ArrayList<>();
        results.add(shouldSort ? SORTEDHEADER : HEADER);
        results.addAll(IntStream.range(0, input.size())
                      .mapToObj(i -> (shouldSort ? (i + 1) + "; " : "") + formatter.formatDriver(input.get(i), shouldSort))
                      .collect(Collectors.toList()));
        return results;
    }

}
