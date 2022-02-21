package util;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for representing time stamps
 */
public class TimeUtils {

    
    private static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * Format a LocalTime instance into the string format "HH:mm:ss"
     * @param time LocalTime instance
     * @return String representing a time stamp
     */
    public static String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Format ta Duration instance into the string format "HH:mm:ss"
     * @param duration Duration instance
     * @return String representing a time stamp
     */
    public static String formatTime(Duration duration) {
        long s = duration.getSeconds();
        return String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
    }

}
