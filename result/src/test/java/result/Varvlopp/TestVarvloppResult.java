package result.Varvlopp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestVarvloppResult {
    VarvloppResult vlr;

    @BeforeEach
    public void setup() {
        vlr = new VarvloppResult();
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
        for(VarvloppDriver vd: vlr.drivers){
            System.out.println(vd.toString());
        }
    }
}