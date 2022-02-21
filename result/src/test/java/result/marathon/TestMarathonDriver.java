package result.marathon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.config.Config;
import result.config.Lap;
import result.config.Marathon;
import result.lap.LapDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMarathonDriver {
  private MarathonDriver driver;
  private String driverNumber;
  String startTime = "../Acceptanstester/M/4/input/starttider.txt";
  String goalTime = "../Acceptanstester/M/4/input/maltider.txt";
  private Config config;

  @BeforeEach
  public void setup() {
    driverNumber = "1";
    Marathon marathon = new Marathon("00:15:00", startTime, goalTime);
    config = new Config();
    config.setMarathon(marathon);
    driver = new MarathonDriver(driverNumber, config);
  }

  @Test
  public void testToStringWithoutTimes() {
    assertEquals("1; MISSING; --:--:--; Start?; Slut?", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testToStringWithTimes() {
    driver.addStartTime("12:00:00");
    driver.addGoalTime("12:30:00");
    assertEquals("1; MISSING; 00:30:00; 12:00:00; 12:30:00", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testToStringWithDriverName() {
    driver.addStartTime("12:00:00");
    driver.addGoalTime("12:30:00");
    driver.setName("Anders Asson");
    assertEquals("1; Anders Asson; 00:30:00; 12:00:00; 12:30:00", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testMultipleStartTimes() {
    driver.addStartTime("12:00:00");
    driver.addStartTime("12:03:00");
    driver.addStartTime("12:10:00");
    driver.addGoalTime("15:00:00");
    assertEquals(
        "1; MISSING; 03:00:00; 12:00:00; 15:00:00; Flera starttider? 12:03:00 12:10:00",
        driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testMultipleGoalTimes() {
    driver.addStartTime("12:00:00");
    driver.addGoalTime("15:00:00");
    driver.addGoalTime("15:30:00");
    driver.addGoalTime("16:00:00");
    assertEquals(
        "1; MISSING; 03:00:00; 12:00:00; 15:00:00; Flera måltider? 15:30:00 16:00:00",
        driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testMultipleErrorColumns() {
    driver.addStartTime("12:00:00");
    driver.addStartTime("13:00:00");
    driver.addGoalTime("15:00:00");
    driver.addGoalTime("15:30:00");
    driver.addGoalTime("16:00:00");
    assertEquals(
        "1; MISSING; 03:00:00; 12:00:00; 15:00:00; Flera starttider? 13:00:00, Flera måltider? 15:30:00 16:00:00",
        driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testMinimumTime() {
    driver.addStartTime("12:00:00");
    driver.addGoalTime("12:10:00");
    assertEquals("1; MISSING; 00:10:00; 12:00:00; 12:10:00; Omöjlig Totaltid?", driver.toString());
    System.out.println(driver);
  }

  @Test
  public void testDriverComparator() {
    driver.addStartTime("13:00:00");
    driver.addGoalTime("15:15:00");
    MarathonDriver newDriver = new MarathonDriver("2", config);
    newDriver.addStartTime("13:00:00");
    newDriver.addGoalTime("15:00:00");
    assertTrue(driver.compareTo(newDriver) > 0);
  }

  @Test
  public void testDriverComparatorErrors() {
    driver.addStartTime("13:00:00");
    driver.addGoalTime("15:00:00");
    MarathonDriver newDriver = new MarathonDriver("2", config);
    newDriver.addStartTime("13:00:00");
    newDriver.addGoalTime("14:00:00");
    newDriver.addGoalTime("14:30:00");
    assertTrue(driver.compareTo(newDriver) < 0);
  }

  @Test
  public void testDriverComparatorSameTime() {
    driver.addStartTime("13:00:00");
    driver.addGoalTime("15:00:00");
    MarathonDriver newDriver = new MarathonDriver("2", config);
    newDriver.addStartTime("13:00:00");
    newDriver.addGoalTime("15:00:00");
    assertEquals(0, driver.compareTo(newDriver));
  }

  @Test
  public void testDriverComparatorBothErrors() {
    driver.addStartTime("13:00:00");
    driver.addGoalTime("15:00:00");
    driver.addGoalTime("15:10:00");
    MarathonDriver newDriver = new MarathonDriver("2", config);
    newDriver.addStartTime("13:00:00");
    newDriver.addStartTime("13:10:00");
    newDriver.addGoalTime("15:00:00");
    assertEquals(0, driver.compareTo(newDriver));
  }
}
