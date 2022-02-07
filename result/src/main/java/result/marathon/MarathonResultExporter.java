package result.marathon;

import result.ResultFormatter;
import result.config.Config;
import util.FileWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Marathon implementation of the Driver class. */
public class MarathonResultExporter {

  public static String HEADER = "StartNr; Namn; Totaltid; Starttid; Måltid";

  private static ResultFormatter formatter = new MarathonFormatter();

  public static void export(Config config, List<MarathonResult> results)
      throws java.io.IOException {
    List<String> lines = toStringList(results);
    lines.add(0, config.getTitle() + "\n");
    lines.add("\nResultatgenerering av team 05");
    FileWriter.dump(config, lines);
  }

  private static List<String> toStringList(List<MarathonResult> input) {
    List<String> results = new ArrayList<>();
    results.add(HEADER);
    results.addAll(
        input.stream().map(result -> formatter.formatDriver(result)).collect(Collectors.toList()));
    return results;
  }
}
