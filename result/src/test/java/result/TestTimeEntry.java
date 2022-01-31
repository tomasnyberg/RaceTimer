package result;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestTimeEntry {

    @Test
    public void testGetters() {

        String startNbr = "01";
        LocalTime localTime = LocalTime.parse("12:00:00");

        TimeEntry timeEntry = new TimeEntry(startNbr, localTime);

        assertEquals(startNbr, timeEntry.getNumber());
        assertEquals(localTime, timeEntry.getTime());

    }

    @Test
    public void testToString() {

        String startNbr = "01";
        String timeString = "12.00.00";
        LocalTime localTime = LocalTime.parse(timeString.replace(".", ":"));

        TimeEntry timeEntry = new TimeEntry(startNbr, localTime);

        assertEquals(startNbr+"; "+timeString, timeEntry.toString());

    }

    @Test
    public void testEquals() {

        TimeEntry timeEntry1 = new TimeEntry("01", LocalTime.parse("12:00:00"));
        TimeEntry timeEntry2 = new TimeEntry("01", LocalTime.parse("12:00:00"));
        TimeEntry timeEntry3 = new TimeEntry("01", LocalTime.parse("16:00:00"));
        TimeEntry timeEntry4 = new TimeEntry("03", LocalTime.parse("12:00:00"));

        assertTrue(timeEntry1.equals(timeEntry2));
        assertTrue(timeEntry1.equals(timeEntry3));
        assertFalse(timeEntry1.equals(timeEntry4));
        assertFalse(timeEntry1.equals("not the correct object type"));

    }

}
