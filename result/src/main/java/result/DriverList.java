package result;

import java.util.ArrayList;

public class DriverList {

    private ArrayList<DriverEntry> driverList;

    public DriverList() {
        this.driverList = new ArrayList<DriverEntry>();
    }

    public void add(DriverEntry entry) {
        driverList.add(entry);
    }

    public DriverEntry get(int i) {
        return driverList.get(i);
    }

    public int size() {
        return driverList.size();
    }
}
