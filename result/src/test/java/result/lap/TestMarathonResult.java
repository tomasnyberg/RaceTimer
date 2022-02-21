package result.lap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.AbstractDriver;
import result.config.Config;
import result.config.Marathon;
import result.marathon.MarathonResult;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMarathonResult {
  private MarathonResult mr;
  Config config;
  String startTimes = "../Acceptanstester/M/1/input/starttider.txt";
  String endTimes = "../Acceptanstester/M/1/input/maltider.txt";

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
    assertEquals(5, mr.drivers.size());
    String startTimesString = """
                            1; 12:00:00
                            2; 12:01:00
                            3; 12:02:00
                            4; 12:03:00
                            5; 12:04:00
                            """;
    assertEquals(mr.toString(), startTimesString);
  }

//   @Test
//   public void testReadEndTimes() {
//     lr.readEndTimes();
//     assertEquals(5, lr.drivers.size());
//   }

//   @Test
//   public void testReadStartAndEndTimes() {
//     lr.readStartTimes();
//     lr.readEndTimes();
//     assertEquals(5, lr.drivers.size());
//   }

//   @Test
//   public void testReadNames() {
//     lr.readNames();
//     assertEquals(5, lr.drivers.size());
//     lr.readStartTimes();
//     lr.readEndTimes();
//     for (AbstractDriver vd : lr.drivers) {
//       System.out.println(vd.toString());
//     }
  
}
