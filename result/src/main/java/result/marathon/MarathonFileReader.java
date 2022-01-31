package result.marathon;

import java.util.List;

import result.DriverEntry;
import result.TimeEntry;
import util.AbstractFileReader;
import util.NameFileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;

public class MarathonFileReader extends AbstractFileReader {
    
    
    private static TimeEntry generateTimeEntry(String line) {
        String[] split = line.split("; ");
        String[] times = split[1].split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);
        LocalTime lt = LocalTime.of(hour, minute, second);
        return new TimeEntry(split[0], lt);

    }

    private static List<TimeEntry> generateTimeEntries(List<String> lines){
        List<TimeEntry> result = new ArrayList<>();
        for(String s: lines){
            result.add(generateTimeEntry(s));
        }
        return result;
    }

    public static List<MarathonResult> result(String startTimeFile, String endTimeFile, String minimumTime){
        List<TimeEntry> startTimeEntries = generateTimeEntries(readFile(startTimeFile));
        List<TimeEntry> endTimeEntries = generateTimeEntries(readFile(endTimeFile));
        // TODO get this from the right place, as specified from task M.2
        List<DriverEntry> driverEntries = NameFileReader.result("../namnfil.txt");
        MarathonMatcher mm = new MarathonMatcher(minimumTime);
        mm.addStartTimes(startTimeEntries);
        mm.addEndTimes(endTimeEntries);
        mm.addDrivers(driverEntries);
        return mm.result();
    }
    
}
