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
        assertEquals("1; MISSING; 0; TODO; TODO; TODO", driver.toString());
    }
}