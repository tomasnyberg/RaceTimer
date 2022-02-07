package result.Varvlopp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestVarvloppResult {
    VarvloppResult vlr;

    @BeforeEach
    public void setup() {
        vlr = new VarvloppResult();
        //TODO : fetch this from config class instead of hardcoding
    }
    
    @Test
    public void testReadStartTimes(){
        vlr.readStartTimes("../Acceptanstester/V/1/input/starttider.txt");
        assertEquals(5, vlr.drivers.size());
        for(VarvloppDriver vd: vlr.drivers){
            System.out.println(vd.toString());
        }
    }

}