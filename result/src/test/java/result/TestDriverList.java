package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.marathon.MarathonMatcher;
import result.marathon.MarathonResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDriverList {

    private DriverList driverList;

    @BeforeEach
    public void setup() {
        driverList = new DriverList();
    }

    @Test
    public void testEmptyList() {
        assertEquals(driverList.size(), 0);
    }

    @Test
    public void testOneEntry() {
        driverList.add(new DriverEntry("1", "Bertil"));

        assertEquals(driverList.size(), 1);
        assertEquals(driverList.get(0).getNumber(), "1");
        assertEquals(driverList.get(0).getName(), "Bertil");
    }
}
