package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import result.marathon.MarathonFileReader;
import result.marathon.MarathonResult;
import java.util.List;


public class TestMarathonFileReader {
    private List<MarathonResult> list;

    @BeforeEach
    public void setUp(){
        String startFile = "../textfiles/starttider.txt";
        String endFile = "../textfiles/maltider.txt";
        list = MarathonFileReader.result(startFile, endFile);
    }

    @Test
    public void testReadsAll(){
        assertEquals(5 , list.size(), "incorrect amount of items");
    }

    @Test
    public void testFirstStartTimes(){
        assertEquals("12:00:00", list.get(0).getStart());
        assertEquals("12:01:00", list.get(1).getStart());
        assertEquals("12:02:00", list.get(2).getStart());
    }

    @Test
    public void testFirstEndTimes(){
        assertEquals("13:23:34", list.get(0).getEnd());
        assertEquals("13:15:16", list.get(1).getEnd());
        assertEquals("13:05:06", list.get(2).getEnd());
    }
}
