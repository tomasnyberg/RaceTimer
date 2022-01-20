package result;

/**
 * Container for raw data read from files, pertaining to a specific driver.
 * Sub classes for different driver types, e.g. MarathonDriver.
 */
public abstract class Driver {

    protected final String startNumber;
    private String name;

    public Driver(String startNumber) {
        this.startNumber = startNumber;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
