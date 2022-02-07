package result.Varvlopp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.config.Config;

import static org.junit.jupiter.api.Assertions.*;

public class TestVarvloppResult {
    VarvloppResult vlr;
    Config config;

    @BeforeEach
    public void setup() {
        config = new Config();
        vlr = new VarvloppResult(config);
    }
    
    @Test
    public void testReadStartTimes(){
        vlr.readStartTimes("../Acceptanstester/V/1/input/starttider.txt");
        assertEquals(5, vlr.drivers.size());
        // for(VarvloppDriver vd: vlr.drivers){
        //     System.out.println(vd.toString());
        // }
    }

    @Test
    public void testReadEndTimes() {
        vlr.readEndTimes("../Acceptanstester/V/1/input/maltider.txt");
        assertEquals(5, vlr.drivers.size());
        // for(VarvloppDriver vd: vlr.drivers){
        //     System.out.println(vd.toString());
        // }
    }

    @Test
    public void testReadStartAndEndTimes() {
        vlr.readStartTimes("../Acceptanstester/V/1/input/starttider.txt");
        vlr.readEndTimes("../Acceptanstester/V/1/input/maltider.txt");
        assertEquals(5, vlr.drivers.size());
    }
    
    @Test
    public void testReadNames(){
        vlr.readNames("../Acceptanstester/V/1/input/namnfil.txt");
        assertEquals(5, vlr.drivers.size());
        vlr.readStartTimes("../Acceptanstester/V/1/input/starttider.txt");
        vlr.readEndTimes("../Acceptanstester/V/1/input/maltider.txt");
        for(VarvloppDriver vd: vlr.drivers){
            System.out.println(vd.toString());
        }
    }
}