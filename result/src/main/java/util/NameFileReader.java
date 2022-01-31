package util;

import java.util.ArrayList;
import java.util.List;

import result.DriverEntry;

public class NameFileReader extends AbstractFileReader {

    public static List<DriverEntry> result(String path) {
        List<DriverEntry> result = new ArrayList<>();
        List<String> list = readFile(path);
        for(int i = 1; i < list.size(); i++){
            String[] split = list.get(i).split("; ");
            result.add(new DriverEntry(split[0], split[1]));
        }
        return result;
    }
}