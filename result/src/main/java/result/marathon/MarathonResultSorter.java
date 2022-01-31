package result.marathon;

import result.marathon.MarathonResult;
import result.marathon.MarathonResultRow;
import util.ResultSorter;

import java.util.ArrayList;
import java.util.List;

public class MarathonResultSorter extends ResultSorter<MarathonResult> {


    @Override
    public List<MarathonResult> sortResults(List<MarathonResult> results) {
        List<MarathonResult> sorted = new ArrayList<>(results);
        sorted.sort((mr1, mr2) -> mr1.getTotal().compareTo(mr2.getTotal()));
        return sorted;
    }


}
