package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.config.Config;
import result.marathon.MarathonResult;
import result.marathon.MarathonResultExporter;
import result.marathon.MarathonResultRow;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMarathonResultExporter {

  private String fileName = "testMarathonResultExporter.txt";
  private List<MarathonResult> list = new ArrayList<>();
  private List<String> expected = new ArrayList<>();
  private Config config = new Config();

  private List<String> readAllLines() throws java.io.IOException {
    return Files.readAllLines(new File(fileName).toPath());
  }

  @BeforeEach
  public void setup() {
    config.setTitle("Test Race");
    config.setResultFile(fileName);
    expected.add(config.getTitle());
    expected.add("");
    expected.add(MarathonResultExporter.HEADER);
  }

  @Test
  public void testExportEmptyList() throws java.io.IOException {
    MarathonResultExporter.export(config, list);
    expected.add("");
    expected.add("Resultatgenerering av team 05");
    assertEquals(readAllLines(), expected);
  }

  @Test
  public void testExport() throws java.io.IOException {

    list.add(
        new MarathonResultRow(
            "12", "test1", LocalTime.parse("11:00:00"), LocalTime.parse("12:00:00")));
    list.add(
        new MarathonResultRow(
            "15", "test2", LocalTime.parse("09:00:00"), LocalTime.parse("12:30:00")));
    list.add(
        new MarathonResultRow(
            "1", "test3", LocalTime.parse("02:00:00"), LocalTime.parse("10:00:01")));
    MarathonResultExporter.export(config, list);

    expected.add("12; test1; 01:00:00; 11:00:00; 12:00:00 ");
    expected.add("15; test2; 03:30:00; 09:00:00; 12:30:00 ");
    expected.add("1; test3; 08:00:01; 02:00:00; 10:00:01 ");

    expected.add("");
    expected.add("Resultatgenerering av team 05");

    assertEquals(readAllLines(), expected);
  }
}
