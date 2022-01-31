package util;

import result.TimeEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileReader {

    protected static List<String> readFile(String path){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> list = new ArrayList<>();
            String line = reader.readLine();
            while(line != null){
                list.add(line);
                line = reader.readLine();
            }
            return list;
        } catch (Exception e){
            System.out.println("Could not find path");
            return null;
        }
    }

    protected static TimeEntry generateTimeEntry(String line) {
        String[] split = line.split("; ");
        String[] times = split[1].split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);
        LocalTime lt = LocalTime.of(hour, minute, second);
        return new TimeEntry(split[0], lt);

    }
}
