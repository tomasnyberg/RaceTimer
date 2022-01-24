package util;

import result.DriverEntry;
import result.DriverList;

public class NameFileReader extends AbstractFileReader{

    @Override
    public String file() {
        return "C:\\Users\\joel2\\IdeaProjects\\team05\\result\\src\\test\\java\\result\\namnfil.txt";
    }

    @Override
    public ArrayList<DriverEntry> CreateObject() {
        DriverList driverList = new DriverList();
        for(int i=2; i < data.size(); i+=2){
            DriverEntry driver = new DriverEntry(data.get(i), data.get(i+1));
            driverList.add(driver);
        }
        return driverList;
    }
}
