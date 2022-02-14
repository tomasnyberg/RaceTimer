package result.Varvlopp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.config.Config;
import result.config.Marathon;
import result.config.Lap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestVarvloppResult {
  VarvloppResult vlr;
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
    vlr = new VarvloppResult(config);
  }

  @Test
  public void testReadStartTimes() {
    vlr.readStartTimes();
    assertEquals(5, vlr.drivers.size());
    // for(VarvloppDriver vd: vlr.drivers){
    //     System.out.println(vd.toString());
    // }
  }

  @Test
  public void testReadEndTimes() {
    vlr.readEndTimes();
    assertEquals(5, vlr.drivers.size());
    // for(VarvloppDriver vd: vlr.drivers){
    //     System.out.println(vd.toString());
    // }
  }

  @Test
  public void testReadStartAndEndTimes() {
    vlr.readStartTimes();
    vlr.readEndTimes();
    assertEquals(5, vlr.drivers.size());
  }

  @Test
  public void testReadNames() {
    vlr.readNames();
    assertEquals(5, vlr.drivers.size());
    vlr.readStartTimes();
    vlr.readEndTimes();
    for (VarvloppDriver vd : vlr.drivers) {
      System.out.println(vd.toString());
    }
  }
}
