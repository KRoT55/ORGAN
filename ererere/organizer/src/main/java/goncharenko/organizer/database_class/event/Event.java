package goncharenko.organizer.database_class.event;

import goncharenko.organizer.database_class.StoreableInDatabase;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements StoreableInDatabase, Comparable {

    private EventName name;
    private EventDate date;
    private EventTime time;
    private int eventTypeId;

    public Event(EventName name, EventDate date, EventTime time, int typeID) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.eventTypeId = typeID;
    }

    public EventName getName() {
        return name;
    }

    public EventDate getDate() {
        return date;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public int hashCode() {
        return name.hashCode() + date.hashCode() + eventTypeId * 1000;
    }

    public String toString() {
        return name.toString() + " " + date.toString();
    }

    public int compareTo(Object eventObjectToCompare) {
        Event e = (Event) eventObjectToCompare;
        if (date.compareTo(e.getDate()) != 0) {
            return date.compareTo(e.getDate());
        } else {
            return getEventTime().compareTo(((Event) eventObjectToCompare).getEventTime());
        }
    }

    public LocalTime getEventTime() {
        return time.getEventTime();
    }

    public LocalTime getEventEndTime() {
        return time.getEventEndTime();
    }

    public LocalDate getBeginDate() {
        return date.getBeginDate();
    }

    public LocalDate getEndDate() {
        return date.getEndDate();
    }
}
