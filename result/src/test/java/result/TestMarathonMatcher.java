package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.marathon.MarathonMatcher;
import result.marathon.MarathonResult;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMarathonMatcher {

  private MarathonMatcher marathonMatcher;
  private List<DriverEntry> drivers;
  private List<TimeEntry> startTimes;
  private List<TimeEntry> endTimes;
  private List<MarathonResult> result;
  private String minimumTime = "00:15:00";

  @BeforeEach
  public void setup() {
    marathonMatcher = new MarathonMatcher(minimumTime);
    drivers = new ArrayList<>();
    startTimes = new ArrayList<>();
    endTimes = new ArrayList<>();
  }

  @Test
  public void testSimplePairing() {

    startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
    endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));

    marathonMatcher.addStartTimes(startTimes);
    marathonMatcher.addEndTimes(endTimes);

    result = marathonMatcher.result();

    assertEquals(1, result.size());

    MarathonResult r = result.get(0);
    assertEquals("01", r.getNumber());
    assertEquals("12:00:00", r.getStart());
    assertEquals("13:00:00", r.getEnd());
  }

  @Test
  public void testOrderedList() {

    startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
    startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
    startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
    startTimes.add(new TimeEntry("04", LocalTime.parse("12:03:00")));
    startTimes.add(new TimeEntry("05", LocalTime.parse("12:04:00")));

    endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
    endTimes.add(new TimeEntry("02", LocalTime.parse("12:49:23")));
    endTimes.add(new TimeEntry("03", LocalTime.parse("13:03:19")));
    endTimes.add(new TimeEntry("04", LocalTime.parse("12:58:34")));
    endTimes.add(new TimeEntry("05", LocalTime.parse("12:59:01")));

    marathonMatcher.addStartTimes(startTimes);
    marathonMatcher.addEndTimes(endTimes);

    result = marathonMatcher.result();

    assertEquals(5, result.size());

    MarathonResult r = result.get(0);
    assertEquals("01", r.getNumber());
    assertEquals("12:00:00", r.getStart());
    assertEquals("13:00:00", r.getEnd());

    r = result.get(1);
    assertEquals("02", r.getNumber());
    assertEquals("12:01:00", r.getStart());
    assertEquals("12:49:23", r.getEnd());

    r = result.get(2);
    assertEquals("03", r.getNumber());
    assertEquals("12:02:00", r.getStart());
    assertEquals("13:03:19", r.getEnd());

    r = result.get(3);
    assertEquals("04", r.getNumber());
    assertEquals("12:03:00", r.getStart());
    assertEquals("12:58:34", r.getEnd());

    r = result.get(4);
    assertEquals("05", r.getNumber());
    assertEquals("12:04:00", r.getStart());
    assertEquals("12:59:01", r.getEnd());
  }

  @Test
  public void testUnOrderedList() {

    startTimes.add(new TimeEntry("04", LocalTime.parse("12:03:00")));
    startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
    startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
    startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
    startTimes.add(new TimeEntry("05", LocalTime.parse("12:04:00")));

    endTimes.add(new TimeEntry("04", LocalTime.parse("12:58:34")));
    endTimes.add(new TimeEntry("02", LocalTime.parse("12:49:23")));
    endTimes.add(new TimeEntry("03", LocalTime.parse("13:03:19")));
    endTimes.add(new TimeEntry("05", LocalTime.parse("12:59:01")));
    endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));

    marathonMatcher.addStartTimes(startTimes);
    marathonMatcher.addEndTimes(endTimes);

    result = marathonMatcher.result();

    assertEquals(5, result.size());

    MarathonResult r = result.get(0);
    assertEquals("01", r.getNumber());
    assertEquals("12:00:00", r.getStart());
    assertEquals("13:00:00", r.getEnd());

    r = result.get(1);
    assertEquals("02", r.getNumber());
    assertEquals("12:01:00", r.getStart());
    assertEquals("12:49:23", r.getEnd());

    r = result.get(2);
    assertEquals("03", r.getNumber());
    assertEquals("12:02:00", r.getStart());
    assertEquals("13:03:19", r.getEnd());

    r = result.get(3);
    assertEquals("04", r.getNumber());
    assertEquals("12:03:00", r.getStart());
    assertEquals("12:58:34", r.getEnd());

    r = result.get(4);
    assertEquals("05", r.getNumber());
    assertEquals("12:04:00", r.getStart());
    assertEquals("12:59:01", r.getEnd());
  }

  @Test
  public void testTotalTime() {

    startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
    endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));

    marathonMatcher.addStartTimes(startTimes);
    marathonMatcher.addEndTimes(endTimes);

    result = marathonMatcher.result();

    MarathonResult resultRow = result.get(0);
    assertEquals("01:00:00", resultRow.getTotal());
  }

  @Test
  public void testImpossibleTotalTime() {

    startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
    endTimes.add(new TimeEntry("01", LocalTime.parse("12:05:00")));

    marathonMatcher.addStartTimes(startTimes);
    marathonMatcher.addEndTimes(endTimes);

    result = marathonMatcher.result();

    MarathonResult resultRow = result.get(0);
    assertEquals("Omöjlig Totaltid?", resultRow.getErrors().get(0));
  }

  @Test
  public void testMissingStart() {
    drivers.add(new DriverEntry("01", "Adam Asson"));
    drivers.add(new DriverEntry("02", "Bodil Bsson"));
    drivers.add(new DriverEntry("03", "Ceasar Csson"));
    startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
    // missing start time
    // startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
    startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
    endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
    endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
    endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

    marathonMatcher.addDrivers(drivers);
    marathonMatcher.addStartTimes(startTimes);
    marathonMatcher.addEndTimes(endTimes);

    result = marathonMatcher.result();

    assertEquals(3, result.size());

    assertEquals("12:00:00", result.get(0).getStart());
    assertEquals("Start?", result.get(1).getStart());
    assertEquals("12:02:00", result.get(2).getStart());
  }

  @Test
  public void testMissingEndTime() {

    drivers.add(new DriverEntry("01", "Adam Asson"));
    drivers.add(new DriverEntry("02", "Bodil Bsson"));
    drivers.add(new DriverEntry("03", "Ceasar Csson"));
    startTimes.add(new TimeEntry("01", LocalTime.parse("12:00:00")));
    startTimes.add(new TimeEntry("02", LocalTime.parse("12:01:00")));
    startTimes.add(new TimeEntry("03", LocalTime.parse("12:02:00")));
    endTimes.add(new TimeEntry("01", LocalTime.parse("13:00:00")));
    // missing end time
    //        endTimes.add(new TimeEntry("02", LocalTime.parse("13:00:00")));
    endTimes.add(new TimeEntry("03", LocalTime.parse("13:00:00")));

    marathonMatcher.addDrivers(drivers);
    marathonMatcher.addStartTimes(startTimes);
    marathonMatcher.addEndTimes(endTimes);

    result = marathonMatcher.result();

    assertEquals(3, result.size());

    assertEquals("13:00:00", result.get(0).getEnd());
    assertEquals("Slut?", result.get(1).getEnd());
    assertEquals("13:00:00", result.get(2).getEnd());
  }
}
