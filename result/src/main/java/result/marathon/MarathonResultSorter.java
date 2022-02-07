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
            // If mr1 is missing start/end time and mr2 is not
            if(holdingErrorThatAffectsSorting(mr1) && !holdingErrorThatAffectsSorting(mr2)) return 1;

            // If mr2 is missing start/end time and mr1 is not
            if(!holdingErrorThatAffectsSorting(mr1) && holdingErrorThatAffectsSorting(mr2)) return -1;

            // If neither mr1 or mr2 are missing start/end time
            return mr1.getTotal().compareTo(mr2.getTotal());
        }

        private boolean holdingErrorThatAffectsSorting(MarathonResult mr) {
            return mr.getStart().contains("Start?") || mr.getEnd().contains("Slut?");
        }
    }
}
