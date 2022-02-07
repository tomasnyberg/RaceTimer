package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.marathon.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMarathonResultExporter {

    private String fileName = "testMarathonResultExporter.txt";
    private List<MarathonResult> list = new ArrayList<>();
    private List<String> expected = new ArrayList<>();

    private MarathonMatcher marathonMatcher;
    private MarathonFormatter formatter;
    private List<DriverEntry> drivers;
    private List<TimeEntry> startTimes;
    private List<TimeEntry> endTimes;
    private List<MarathonResult> result;
    private String minimumTime = "00:00:00";

    private List<String> readAllLines() throws java.io.IOException
    {
        return Files.readAllLines(new File(fileName).toPath());
    }

    @BeforeEach
    public void setup() {
        expected.add(MarathonResultExporter.HEADER);
        marathonMatcher = new MarathonMatcher(minimumTime);
        formatter = new MarathonFormatter();
        drivers = new ArrayList<>();
        startTimes = new ArrayList<>();
        endTimes = new ArrayList<>();
    }

    @Test
    public void testExportEmptyList() throws java.io.IOException {
        MarathonResultExporter.export(fileName, list, false);
        assertEquals(readAllLines(), expected);
    }

    @Test
    public void testExport() throws java.io.IOException {

        list.add(new MarathonResultRow("12", "test1", LocalTime.parse("11:00:00"), LocalTime.parse("12:00:00")));
        list.add(new MarathonResultRow("15", "test2", LocalTime.parse("09:00:00"), LocalTime.parse("12:30:00")));
        list.add(new MarathonResultRow("1", "test3", LocalTime.parse("02:00:00"), LocalTime.parse("10:00:01")));
        MarathonResultExporter.export(fileName, list, false);

        expected.add("12; test1; 01:00:00; 11:00:00; 12:00:00 ");
        expected.add("15; test2; 03:30:00; 09:00:00; 12:30:00 ");
        expected.add("1; test3; 08:00:01; 02:00:00; 10:00:01 ");

        assertEquals(readAllLines(), expected);

    }

    @Test
    public void testSortedExport() throws java.io.IOException {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));
        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        marathonMatcher.addDrivers(drivers);
        marathonMatcher.addStartTimes(startTimes);
        marathonMatcher.addEndTimes(endTimes);

        result = marathonMatcher.result();

        MarathonResultExporter.export("testSortedExport.txt", result, true);

        List<String> fileResults = Files.readAllLines(Paths.get("testSortedExport.txt"));

        String expectedResult1 = "1; 01; Adam Asson; 01:00:00; 12:00:00; 13:00:00";
        String expectedResult2 = "2; 02; Bodil Bsson; --:--:--; --:--:--; 13:00:00";
        String expectedResult3 = "3; 03; Caesar Csson; 00:58:00; 12:02:00; 13:00:00";

        assertEquals(expectedResult1, fileResults.get(1));
        assertEquals(expectedResult2, fileResults.get(2));
        assertEquals(expectedResult3, fileResults.get(3));
    }

}
