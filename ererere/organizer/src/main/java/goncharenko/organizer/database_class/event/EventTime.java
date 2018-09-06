package goncharenko.organizer.database_class.event;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EventTime {
    private LocalTime eventTime;
    private LocalTime eventEndTime;
    private DateTimeFormatter timeFormatter;

    public EventTime(String eventTime, String eventEndTime) {
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        this.eventTime = LocalTime.parse(eventTime, timeFormatter);
        this.eventEndTime = LocalTime.parse(eventEndTime, timeFormatter);
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public LocalTime getEventEndTime() {
        return eventEndTime;
    }

    public int hashCode() {
        return eventTime.hashCode();
    }


}
