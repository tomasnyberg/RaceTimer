package result.lap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.AbstractDriver;
import result.config.Config;
import result.config.Lap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestLapResult {
  private LapResult lr;
  Config config;
  String startTime = "../Acceptanstester/V/1/input/starttider.txt";
  List<String> endTimes = new ArrayList<>();

  @BeforeEach
  public void setup() {
    endTimes.add("../Acceptanstester/V/2/input/maltider1.txt");
    endTimes.add("../Acceptanstester/V/2/input/maltider2.txt");
    config = new Config();
    config.setNameFile("../Acceptanstester/V/1/input/namnfil.txt");
    config.setLap(new Lap("15:00:00", false, "00:00:00", "01:00:00", startTime, endTimes));
    lr = new LapResult(config);
  }

  @Test
  public void testReadStartTimes() {
    lr.readStartTimes();
    assertEquals(5, lr.drivers.size());
  }

  @Test
  public void testReadEndTimes() {
    lr.readEndTimes();
    assertEquals(5, lr.drivers.size());
  }

  @Test
  public void testReadStartAndEndTimes() {
    lr.readStartTimes();
    lr.readEndTimes();
    assertEquals(5, lr.drivers.size());
  }

  @Test
  public void testReadNames() {
    lr.readNames();
    assertEquals(5, lr.drivers.size());
    lr.readStartTimes();
    lr.readEndTimes();
    for (AbstractDriver vd : lr.drivers) {
      System.out.println(vd.toString());
    }
  }
}
