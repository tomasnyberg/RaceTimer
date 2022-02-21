package result.lap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.AbstractDriver;
import result.config.Config;
import result.config.Marathon;
import result.marathon.MarathonResult;

import static org.junit.jupiter.api.Assertions.*;

public class TestMarathonResult {
  private MarathonResult mr;
  Config config;
  String startTimes = "../Acceptanstester/M/4/input/starttider.txt";
  String endTimes = "../Acceptanstester/M/4/input/maltider.txt";

  @BeforeEach
  public void setup() {
    config = new Config();
    config.setNameFile("../Acceptanstester/M/4/input/namnfil.txt");
    config.setMarathon(new Marathon("00:15:00", startTimes, endTimes));
    mr = new MarathonResult(config);
  }

  @Test
  public void testReadStartTimes() {
    mr.readStartTimes();
    assertEquals(4, mr.drivers.size());
  }

  @Test
  public void testReadEndTimes() {
    mr.readEndTimes();
    assertEquals(4, mr.drivers.size());
  }

  @Test
  public void testReadStartAndEndTimes() {
    mr.readStartTimes();
    mr.readEndTimes();
    assertEquals(5, mr.drivers.size());
  }

  @Test
  public void testReadNames() {
    mr.readNames();
    assertEquals(5, mr.drivers.size());
    mr.readEndTimes();
    mr.readStartTimes();
    for (AbstractDriver vd : mr.drivers) {
      System.out.println(vd.toString());
    }
  }

  @Test
  public void testGenerateResult() {
    mr.generateResult();
    String finalResultString = "1; Anders Asson; --:--:--; 12:00:00; Slut?\n"
                             + "2; Bengt Bsson; --:--:--; Start?; 13:15:16\n"
                             + "3; Chris Csson; 01:03:06; 12:02:00; 13:05:06; Flera måltider? 13:07:08\n"
                             + "4; David Dsson; 01:09:07; 12:03:00; 13:12:07; Flera starttider? 12:12:00\n"
                             + "5; Erik Esson; 00:12:07; 12:04:00; 12:16:07; Omöjlig Totaltid?\n";
    String mrResultString = "";
    for (AbstractDriver driver : mr.drivers) {
      mrResultString += driver.toString() + "\n";
    }
    assertEquals(finalResultString, mrResultString);
  }
}
