package result.marathon;

import java.util.List;

import result.TimeEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;

public class MarathonFileParser {
    

    private List<String> readFile(String path){
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

    private TimeEntry generateTimeEntry(String line) {

        String[] split = line.split("; ");

        String[] times = split[1].split(":");

        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);

        LocalTime lt = LocalTime.of(hour, minute, second);

        return new TimeEntry(split[0], lt);

    }

    private List<TimeEntry> generateTimeEntries(List<String> lines){

        List<TimeEntry> result = new ArrayList<>();

        for(String s: lines){
            result.add(generateTimeEntry(s));
        }

        return result;
      }

    public static List<MarathonResult> result(String endTimeFile, String startTimeFile){
        return null;
    }
    
}
