package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

import goncharenko.organizer.LocalDateStringConverter;
import goncharenko.organizer.LocalTimeStringConverter;
import goncharenko.organizer.database_class.event.Event;

public class EventFormData {

    private String eventName;
    private String eventDescription;
    private String eventBeginDate;
    private String eventEndDate;
    private String eventBeginTime;
    private String eventEndTime;
    private String eventType;

    //todo event type

    void setEventName(String name) {
        this.eventName = name;
    }

    void setEventDescription(String description) {
        this.eventDescription = description;
    }

    void setEventBeginDate(String date) {
        this.eventBeginDate = date;
    }

    void setEventEndDate(String date) {
        this.eventEndDate = date;
    }

    void setEventBeginTime(String time) {
        this.eventBeginTime = time;
    }

    void setEventEndTime(String time) {
        this.eventEndTime = time;
    }

    void setEventTypeName(String typeString) {
        this.eventType = typeString;
    }


    String getEventName() {
        return eventName;
    }

    String getEventDescription() {
        return eventDescription;
    }

    String getEventBeginDate() {
        return eventBeginDate;
    }

    String getEventEndDate() {
        return eventEndDate;
    }

    String getEventBeginTime() {
        return eventBeginTime;
    }

    String getEventEndTime() {
        return eventEndTime;
    }

    String getEventTypeName() {
        return eventType;
    }

    public void initWith(Event event, String eventTypeString) {
        setEventName(event.getName().getEventName());
        setEventDescription(event.getName().getEventDescription());
        String eventDate = LocalDateStringConverter.toString(event.getDate().getBeginDate());
        setEventBeginDate(eventDate);
        eventDate = LocalDateStringConverter.toString(event.getDate().getEndDate());
        setEventEndDate(eventDate);
        String eventTime = LocalTimeStringConverter.toString(event.getEventTime());
        setEventBeginTime(eventTime);
        eventTime = LocalTimeStringConverter.toString(event.getEventEndTime());
        setEventEndTime(eventTime);
        setEventTypeName(eventTypeString);
    }
}
