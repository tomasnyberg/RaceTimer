package result.marathon.error;

import result.marathon.MarathonResult;

import java.util.ArrayList;
import java.util.List;

/** Decorator pattern to decorate marathon results with error messages. */
public abstract class MarathonDecorator implements MarathonResult {

    protected String ERROR_STRING;
    private MarathonResult result;

    public List<String> errors;

    public MarathonDecorator(MarathonResult result, String ERROR_STRING) {
        this.result = result;
        this.ERROR_STRING = ERROR_STRING;
        errors = new ArrayList<>();
    }

    @Override
    public String getNumber() {
        return result.getNumber();
    }

    @Override
    public String getName() {
        return result.getName();
    }

    @Override
    public String getStart() {
        return result.getStart();
    }

    @Override
    public String getEnd() {
        return result.getEnd();
    }

    @Override
    public String getTotal() {
        return result.getTotal();
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }
}
