package result.marathon;

import result.ResultRow;
import util.TimeUtils;

import java.time.Duration;
import java.time.LocalTime;


// StartNe, Name, Start, End, Duration, erros*


public class MarathonResultRow extends ResultRow implements MarathonResult {
    private LocalTime start;
    private LocalTime end;

    public MarathonResultRow(String number, String name, LocalTime start, LocalTime end) {
        super(number, name);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return TimeUtils.formatTime(start);
    }

    public String getEnd() {
        return TimeUtils.formatTime(end);
    }

    @Override
    public String getTotal() {
        return TimeUtils.formatTime(Duration.between(start, end));
    }

}
