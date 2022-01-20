package result;

import java.util.List;

/**
 * A result for the formatter to consume. Has sub classes for types of races.
 */
public interface Result {

    String getNumber();

    String getName();

    List<String> getErrors();
}
