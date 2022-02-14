package result.Varvlopp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import result.config.Config;
import result.config.Varv;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestVarvloppDriver {
  private VarvloppDriver driver;
  private String driverNumber;
  String startTime = "../Acceptanstester/V/1/input/starttider.txt";
  List<String> endTimes = new ArrayList<>();
  private Config config;

  @BeforeEach
  public void setup() {
    endTimes.add("../Acceptanstester/V/2/input/maltider1.txt");
    endTimes.add("../Acceptanstester/V/2/input/maltider2.txt");
    driverNumber = "1";
    Varv varv = new Varv("14:59:00", false, "00:15:00", "01:00:00", startTime, endTimes);
    config = new Config();
    config.setVarv(varv);
    driver = new VarvloppDriver(driverNumber, config);
  }

  @Test
  public void testToString() {
    assertEquals("1; MISSING; 0; --:--:--; Start?; Slut?", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testGetLappings() {
    driver.addStartTime("12:00:00");
    driver.addEndTime("13:00:00");
    driver.addEndTime("14:00:00");
    driver.addEndTime("15:00:00");
    assertEquals(
        "1; MISSING; 3; 03:00:00; 12:00:00; 13:00:00; 14:00:00; 15:00:00", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testGenerateVarvTimes() {
    driver.setMaxLaps(3);
    driver.addStartTime("12:00:00");
    driver.addEndTime("13:00:00");
    driver.addEndTime("14:30:00");
    driver.addEndTime("15:00:00");
    assertEquals("1; MISSING; 3; 03:00:00; 01:00:00; 01:30:00; 00:30:00; 12:00:00; 13:00:00; 14:30:00; 15:00:00", driver.toString());
    System.out.println(driver);
  }
  
  @Test
  public void testGreaterMaxLaps() {
    driver.setMaxLaps(5);
    driver.addStartTime("12:00:00");
    driver.addEndTime("13:00:00");
    driver.addEndTime("14:30:00");
    driver.addEndTime("15:00:00");
    assertEquals("1; MISSING; 3; 03:00:00; 01:00:00; 01:30:00; 00:30:00; ; ; 12:00:00; 13:00:00; 14:30:00; ; ; 15:00:00", driver.toString());
    System.out.println(driver);
  }
  
  @Test
  public void testMissingStart() {
    driver.setMaxLaps(3);
    driver.addEndTime("13:00:00");
    driver.addEndTime("14:30:00");
    driver.addEndTime("15:00:00");
    assertEquals("1; MISSING; 3; --:--:--; ; 01:30:00; 00:30:00; Start?; 13:00:00; 14:30:00; 15:00:00", driver.toString());
    System.out.println(driver);
  }
  
  @Test
  public void testMissingEnd() {
    driver.setMaxLaps(3);
    driver.addStartTime("12:00:00");
    assertEquals("1; MISSING; 0; --:--:--; ; ; ; 12:00:00; ; ; Slut?", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testMissingGoalTime() {
    driver.setMaxLaps(3);
    driver.addStartTime("12:00:00");
    driver.addEndTime("13:00:00");
    assertEquals("1; MISSING; 1; --:--:--; 01:00:00; ; ; 12:00:00; 13:00:00; ; Slut?", driver.toString());
    System.out.println(driver);
  }
  
  @Test
  public void testMultipleStartTimes() {
    driver.setMaxLaps(3);
    driver.addStartTime("12:00:00");
    driver.addStartTime("12:03:00");
    driver.addStartTime("12:10:00");
    driver.addEndTime("13:00:00");
    driver.addEndTime("14:30:00");
    driver.addEndTime("15:00:00");
    assertEquals("1; MISSING; 3; 03:00:00; 01:00:00; 01:30:00; 00:30:00; 12:00:00; 13:00:00; 14:30:00; 15:00:00; Flera starttider? 12:03:00 12:10:00", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testMultipleGoalTimes() {
    driver.setMaxLaps(3);
    driver.addStartTime("12:00:00");
    driver.addEndTime("15:00:00");
    driver.addEndTime("15:30:00");
    driver.addEndTime("16:00:00");
    assertEquals("1; MISSING; 1; 03:00:00; 03:00:00; ; ; 12:00:00; ; ; 15:00:00; Flera måltider? 15:30:00 16:00:00", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testMultipleErrorColumns() {
    driver.setMaxLaps(3);
    driver.addStartTime("12:00:00");
    driver.addStartTime("13:00:00");
    driver.addEndTime("15:00:00");
    driver.addEndTime("15:30:00");
    driver.addEndTime("16:00:00");
    assertEquals("1; MISSING; 1; 03:00:00; 03:00:00; ; ; 12:00:00; ; ; 15:00:00; Flera starttider? 13:00:00, Flera måltider? 15:30:00 16:00:00", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testMinimumLapTime() {
    driver.setMaxLaps(3);
    driver.addStartTime("12:00:00");
    driver.addEndTime("13:00:00");
    driver.addEndTime("13:10:00");
    driver.addEndTime("15:00:00");
    assertEquals("1; MISSING; 3; 03:00:00; 01:00:00; 00:10:00; 01:50:00; 12:00:00; 13:00:00; 13:10:00; 15:00:00; Omöjlig varvtid?", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testDriverComparator() {
    driver.addStartTime("00:00:00");
    driver.addEndTime("01:00:00");
    VarvloppDriver newDriver = new VarvloppDriver("2", config);
    newDriver.addStartTime("00:00:00");
    newDriver.addEndTime("01:00:00");
    newDriver.addEndTime("02:00:00");
    assertEquals(1, driver.getAmountOfLaps());
    assertTrue(driver.compareTo(newDriver) > 0);
    driver.addEndTime("01:59:00");
    // If they have an equal amount of laps but not a total time, don't sort
    assertEquals(0, driver.compareTo(newDriver));
    newDriver.addEndTime("15:05:00");
    driver.addEndTime("15:06:00");
  }

  @Test
  public void testExactFinalLapTime() {
    driver.setMaxLaps(5);
    driver.addStartTime("12:00:00");
    driver.addEndTime("13:00:00");
    driver.addEndTime("14:30:00");
    driver.addEndTime("14:59:00");
    driver.setName("Anton Asson");
    assertEquals("1; Anton Asson; 3; 02:59:00; 01:00:00; 01:30:00; 00:29:00; ; ; 12:00:00; 13:00:00; 14:30:00; ; ; 14:59:00", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testStartTimeAfterEndTime() {
    driver.setMaxLaps(3);
    driver.addStartTime("13:15:00");
    driver.addEndTime("13:00:00");
    driver.addEndTime("14:30:00");
    driver.addEndTime("15:00:00");
    assertEquals("1; MISSING; 3; 01:45:00; 00:-15:00; 01:30:00; 00:30:00; 13:15:00; 13:00:00; 14:30:00; 15:00:00; Omöjlig varvtid?",
    driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testStartTimeAfterLastEndTime() {
    driver.setMaxLaps(3);
    driver.addStartTime("16:15:00");
    driver.addEndTime("13:00:00");
    driver.addEndTime("14:30:00");
    driver.addEndTime("15:00:00");
    assertEquals("1; MISSING; 3; -1:-15:00; -3:-15:00; 01:30:00; 00:30:00; 16:15:00; 13:00:00; 14:30:00; 15:00:00; Omöjlig varvtid?",
    driver.toString());
    System.out.println(driver);
  }
}
