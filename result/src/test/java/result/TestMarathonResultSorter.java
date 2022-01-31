package result;

import org.junit.jupiter.api.Test;
import result.marathon.MarathonResult;
import result.marathon.MarathonResultSorter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

public class TestMarathonResultSorter {

    @Test
    public void testEmptyList(){
        List<MarathonResult> results = new ArrayList<>();
        List<MarathonResult> newResults = new MarathonResultSorter().sortResults(results);
        assertTrue(results.equals(newResults), "Not an empty result");
    }

}