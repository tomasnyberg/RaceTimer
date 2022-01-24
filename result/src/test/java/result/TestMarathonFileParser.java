package result;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import result.marathon.MarathonFileParser;
import result.marathon.MarathonResult;
import java.util.List;


public class TestMarathonFileParser {

    @Test
    public void testReadsAll(){
        // TODO: link to the correct place in repo, currently hard coded to my local file
        String startFile = "C:\\Users\\ceken\\Downloads\\Acceptanstester (1)\\Acceptanstester\\Maraton\\acceptanstestM1_M3\\starttider.txt";
        String endFile = "C:\\Users\\ceken\\Downloads\\Acceptanstester (1)\\Acceptanstester\\Maraton\\acceptanstestM1_M3\\maltider.txt";
        List<MarathonResult> list = MarathonFileParser.result(startFile, endFile);
        assertEquals(5, list.size());
    }

    
}
