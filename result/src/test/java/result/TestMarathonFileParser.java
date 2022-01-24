package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import result.marathon.MarathonFileReader;
import result.marathon.MarathonResult;
import java.util.List;


public class TestMarathonFileParser {
    private List<MarathonResult> list;

    @BeforeEach
    public void setUp(){
        // TODO: link to the correct place in repo, currently hard coded to my local file
        String startFile = "C:\\Users\\Tomas\\Desktop\\Acceptanstester\\Maraton\\acceptanstestM1\\starttider.txt";
        String endFile = "C:\\Users\\Tomas\\Desktop\\Acceptanstester\\Maraton\\acceptanstestM1\\maltider.txt";
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
