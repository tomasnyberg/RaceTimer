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
  List<MarathonResult> incorrectResults;
  List<MarathonResult> sortedIncorrectResults;
  String minimumTime = "00:00:00";

  @BeforeEach
  public void setUp() {
    String startFile = "../AcceptansTester/Maraton/acceptanstestM1/starttider.txt";
    String endFile = "../AcceptansTester/Maraton/acceptanstestM1/maltider.txt";
    String startFileIncorrect = "../AcceptansTester/Maraton/acceptanstestM4/starttider.txt";
    String endFileIncorrect = "../AcceptansTester/Maraton/acceptanstestM4/maltider.txt";
    results = MarathonFileReader.result(startFile, endFile, minimumTime);
    sortedResults = new MarathonResultSorter().sortResults(results);
    incorrectResults = MarathonFileReader.result(startFileIncorrect, endFileIncorrect, minimumTime);
    sortedIncorrectResults = new MarathonResultSorter().sortResults(incorrectResults);
  }

  @Test
  public void testEmptyList() {
    results = new ArrayList<>();
    List<MarathonResult> newResults = new MarathonResultSorter().sortResults(results);
    assertTrue(results.equals(newResults), "Not an empty result");
  }

  @Test
  public void testFirstTimesAreRight() {
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

  @Test
  public void testIncorrectFileSorting() {
    assertEquals(5, incorrectResults.size());
    String first = sortedIncorrectResults.get(0).getTotal();
    String second = sortedIncorrectResults.get(1).getTotal();
    String third = sortedIncorrectResults.get(2).getTotal();
    String fourth = sortedIncorrectResults.get(3).getTotal();
    String fifth = sortedIncorrectResults.get(4).getTotal();
    assertEquals("00:12:07", first);
    assertEquals("--:--:--", fourth);
    assertEquals("--:--:--", fifth);
  }
}
