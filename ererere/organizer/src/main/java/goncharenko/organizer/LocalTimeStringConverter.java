package goncharenko.organizer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeStringConverter {

    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalTime toLocalTime(String time) {
        return LocalTime.parse(time, timeFormatter);
    }

    public static String toString(LocalTime time) {
        return String.valueOf(time);
    }
}
