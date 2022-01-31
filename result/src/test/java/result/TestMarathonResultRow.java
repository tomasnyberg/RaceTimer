package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.marathon.MarathonResultRow;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestMarathonResultRow {

    private LocalTime startTime = LocalTime.parse("08:20:10");
    private LocalTime endTime = LocalTime.parse("09:10:30");
    private MarathonResultRow resultRow;

    @BeforeEach
    public void setup() {
        resultRow = new MarathonResultRow("1", "Lucas", startTime, endTime);
    }

    @Test
    public void testGetStart() {
        assertEquals(resultRow.getStart(), "08:20:10");
    }

    @Test
    public void testGetEnd() {
        assertEquals(resultRow.getEnd(), "09:10:30");
    }

    @Test
    public void testGetTotal() {
        assertEquals(resultRow.getTotal(), "00:50:20");
    }

}
