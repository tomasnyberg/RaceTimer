package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.marathon.MarathonFormatter;
import result.marathon.MarathonMatcher;
import result.marathon.MarathonResult;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMarathonFormatter {

    private MarathonMatcher marathonMatcher;
    private MarathonFormatter formatter;
    private List<DriverEntry> drivers;
    private List<TimeEntry> startTimes;
    private List<TimeEntry> endTimes;
    private List<MarathonResult> result;

    @BeforeEach
    public void setup() {
        marathonMatcher = new MarathonMatcher();
        formatter = new MarathonFormatter();
        drivers = new ArrayList<>();
        startTimes = new ArrayList<>();
        endTimes = new ArrayList<>();
    }

    @Test
    public void testMissingStart() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));
        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        // missing start time
        //startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        marathonMatcher.addDrivers(drivers);
        marathonMatcher.addStartTimes(startTimes);
        marathonMatcher.addEndTimes(endTimes);

        result = marathonMatcher.result();

        assertEquals(3, result.size());

        String expectedResult1 = "01; Adam Asson; 01:00:00; 12:00:00; 13:00:00; ";
        // String expectedResult2 = "02; Bodil Bsson; --:--:--; Start?; 13:00:00; ";
        String expectedResult3 = "03; Caesar Csson; 00:58:00; 12:02:00; 13:00:00; ";

        assertEquals(expectedResult1, formatter.formatDriver(result.get(0)));
        //assertEquals(expectedResult2, formatter.formatDriver(result.get(1)));
        assertEquals(expectedResult3, formatter.formatDriver(result.get(2)));

    }

    @Test
    public void testMissingEnd() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));
        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        // missing end time
        // endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        marathonMatcher.addDrivers(drivers);
        marathonMatcher.addStartTimes(startTimes);
        marathonMatcher.addEndTimes(endTimes);

        result = marathonMatcher.result();

        assertEquals(3, result.size());

        String expectedResult1 = "01; Adam Asson; 01:00:00; 12:00:00; 13:00:00; ";
        // String expectedResult2 = "02; Bodil Bsson; 12:01:00; Slut?; ";
        String expectedResult3 = "03; Caesar Csson; 00:58:00; 12:02:00; 13:00:00; ";

        assertEquals(expectedResult1, formatter.formatDriver(result.get(0)));
        // assertEquals(expectedResult2, formatter.formatDriver(result.get(1)));
        assertEquals(expectedResult3, formatter.formatDriver(result.get(2)));

    }

    @Test
    public void testMultipleMissingTimes() {
        drivers.add(new DriverEntry("01", "Adam Asson"));
        drivers.add(new DriverEntry("02", "Bodil Bsson"));
        drivers.add(new DriverEntry("03", "Caesar Csson"));
//        startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
//        startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
        startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
        endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
        // missing end time
        // endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
//        endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

        marathonMatcher.addDrivers(drivers);
        marathonMatcher.addStartTimes(startTimes);
        marathonMatcher.addEndTimes(endTimes);

        result = marathonMatcher.result();

        assertEquals(3, result.size());

        /*
        String expectedResult1 = "01; Adam Asson; Start?; 13:00:00; ";
        String expectedResult2 = "02; Bodil Bsson; Start?; Slut?; ";
        String expectedResult3 = "03; Caesar Csson; 12:02:00; Slut?; ";

        assertEquals(expectedResult1, formatter.formatDriver(result.get(0)));
        assertEquals(expectedResult2, formatter.formatDriver(result.get(1)));
        assertEquals(expectedResult3, formatter.formatDriver(result.get(2)));
        */

    }

}
