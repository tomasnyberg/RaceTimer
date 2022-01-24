package result;

import java.util.ArrayList;
import java.util.List;

public class DriverList {

    private ArrayList<DriverEntry> driverList;

    public DriverList() {
        this.driverList = new ArrayList<DriverEntry>();
    }


    public ArrayList<DriverEntry> getList(){return driverList;}

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
