package result.marathon;

import result.marathon.MarathonResult;
import result.marathon.MarathonResultRow;
import result.marathon.error.MarathonDecorator;
import result.marathon.error.MissingEndTime;
import result.marathon.error.MissingStartTime;
import util.ResultSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MarathonResultSorter extends ResultSorter<MarathonResult> {

    @Override
    public List<MarathonResult> sortResults(List<MarathonResult> results) {
        List<MarathonResult> sorted = new ArrayList<>(results);
        // If a result has a decorator, it should go last. Otherwise just compare total
        // times
        sorted.sort(new comp());
        return sorted;
    }

    private class comp implements Comparator<MarathonResult> {

        @Override
        public int compare(MarathonResult mr1, MarathonResult mr2) {
            if (!missingTime(mr1) && !missingTime(mr2)) {
                return mr1.getTotal().compareTo(mr2.getTotal());
            }
            if(missingTime(mr1) && missingTime(mr2)) {
                return mr1.getStart().compareTo(mr2.getStart());
            }
            return missingTime(mr1) ? 1:-1;
        }

        public boolean missingTime(MarathonResult mr) {
            return mr instanceof MissingEndTime || mr instanceof MissingStartTime;
        }

    }
}
