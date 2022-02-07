package result.Varvlopp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestVarvloppDriver {
    private VarvloppDriver driver;
    private String driverNumber;

    @BeforeEach
    public void setup() {
        driverNumber = "1";
        driver = new VarvloppDriver(driverNumber);
    }

    @Test
    public void testToString() {
        System.out.println(driver);
        assertEquals("1; MISSING; 0; --:--:--; TODO; TODO", driver.toString());
    }

    @Test
    public void testGetLappings(){
        driver.addStartTime("12:00:00");
        driver.addEndTime("13:00:00");
        driver.addEndTime("14:00:00");
        driver.addEndTime("15:00:00");
        assertEquals("1; MISSING; 3; 03:00:00; TODO; 13:00:00; 14:00:00; TODO", driver.toString());
        System.out.println(driver);
    }
}