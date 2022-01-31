package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import result.marathon.MarathonFileReader;
import result.marathon.MarathonResult;
import result.marathon.MarathonResultSorter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

public class TestMarathonResultSorter {
    List<MarathonResult> results;
    List<MarathonResult> sortedResults;

    @BeforeEach
    public void setUp(){
        // TODO: change these to correct directory
        results = MarathonFileReader.result("../starttider.txt", "../maltider.txt");
        sortedResults = new MarathonResultSorter().sortResults(results);
    }

    @Test
    public void testEmptyList(){
        results = new ArrayList<>();
        List<MarathonResult> newResults = new MarathonResultSorter().sortResults(results);
        assertTrue(results.equals(newResults), "Not an empty result");
    }

    @Test
    public void testFirstTimesAreRight(){
        String first = sortedResults.get(0).getTotal();
        String second = sortedResults.get(1).getTotal();
        String third = sortedResults.get(2).getTotal();
        // Driver 3 finishes in 12:02:00 -> 13:05:06 = 01:03:06
        assertEquals(first, "01:03:06");
        // Driver 4 finishes in 12:03:00 -> 13:12:07 = 01:08:07
        assertEquals(second, "01:09:07");
        // Driver 5 finishes in 01 12 07 
        assertEquals(third, "01:12:07");
    }

}