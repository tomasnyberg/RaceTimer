package util;

import java.util.List;
import result.ResultRow;

public abstract class ResultSorter <T extends ResultRow>{
    
    /**
     * Takes in a list of ResultRows and sorts these. This is the only public method in the class,
     * the rest of the logic will be implemented in subclasses, such as "MarathonResultSorter"
     *
     * @param <T> Type of ResultRow class, e.g. MarathonResultRow
     */
    public abstract List<T> sortResults(List<T> results);

}