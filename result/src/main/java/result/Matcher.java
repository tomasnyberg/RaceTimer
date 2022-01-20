package result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Combines information about drivers (name, start times, end times) to create a Result.
 * Sub classes for different types of races.
 *
 * @param <D> Type of Driver class, e.g. MarathonDriver
 * @param <R> Type of Result class, e.g. MarathonResult
 */
public abstract class Matcher<D extends Driver, R extends Result> {

    protected Map<String, D> drivers;
    protected List<ErrorFinder<D, R>> errorFinders = new ArrayList<>();

    public Matcher() {
        this.drivers = new TreeMap<>();
    }

    /**
     * Computes a result row for each driver.
     * @return List of results, i.e. one Result for each Driver.
     */
    public abstract List<R> result();

    public abstract void addDrivers(List<DriverEntry> drivers);

    protected R findErrors(D driver, R result) {
        R decoratedResult = result;
        for (ErrorFinder<D, R> ef : errorFinders) {
            decoratedResult = ef.apply(driver, decoratedResult);
        }
        return decoratedResult;
    }


}
