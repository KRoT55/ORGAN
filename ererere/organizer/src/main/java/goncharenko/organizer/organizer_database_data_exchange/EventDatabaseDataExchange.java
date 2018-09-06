package goncharenko.organizer.organizer_database_data_exchange;

import goncharenko.organizer.LocalDateStringConverter;
import goncharenko.organizer.LocalTimeStringConverter;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.database_class.StoreableInDatabase;
import goncharenko.organizer.database_class.event.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EventDatabaseDataExchange implements DatabaseDownloadable {
    private OrganizerDatabase database;
    //query parameters
    private String eventName;
    private String eventDescription;
    private String dateString;
    private String endDateString;
    private String timeString;
    private String endTimeString;
    private int eventTypeId;


    public EventDatabaseDataExchange(OrganizerDatabase database) {
        this.database = database;
    }


    @Override
    public void updateRecord(int eventId, StoreableInDatabase object) {
        getQueryParam((Event) object);
        database.executeUpdateQuery("updateTypeList event set event_name=\"" + eventName
                + "\", event_description=\"" + eventDescription + "\", event_date=\"" + dateString + "\", event_end_date=\"" + endDateString + "\", id_event_type=" + eventTypeId + "" +
                " ,event_time=\"" + timeString + "\" ,event_end_time= \"" + endTimeString + "\" where id_event=" + eventId + ";");
    }

    @Override
    public void deleteRecord(int eventId) {
        database.executeDeleteQuery("delete from event where id_event=" + eventId + ";");
    }

    @Override
    public void addRecord(StoreableInDatabase object) {
        getQueryParam((Event) object);
        database.establishConnection();
        database.executeInsertQuery("insert into event(event_name,event_description,event_date,event_end_date,id_event_type,event_time,event_end_time)" +
                " values(\"" + eventName + "\",\"" + eventDescription + "\",\"" + String.valueOf(dateString) + "\",\"" + String.valueOf(endDateString) + "\"," + eventTypeId + ",\"" + timeString + "\",\"" + endTimeString + "\")");

    }

    @Override
    public ResultSet getAllRecords() {
        return database.executeSelectQuery("select event.* from event;");
    }

    private void getQueryParam(Event eventObject) {
        eventName = eventObject.getName().getEventName();
        eventDescription = eventObject.getName().getEventDescription();
        dateString = LocalDateStringConverter.toString(eventObject.getBeginDate());
        endDateString = LocalDateStringConverter.toString(eventObject.getEndDate());
        eventTypeId = eventObject.getEventTypeId();
        timeString = LocalTimeStringConverter.toString(eventObject.getEventTime());
        endTimeString = LocalTimeStringConverter.toString(eventObject.getEventEndTime());
    }

    public ResultSet getEventBetween(LocalDate from, LocalDate to) {
        dateString = LocalDateStringConverter.toString(from);
        endDateString = LocalDateStringConverter.toString(to);

        return database.executeSelectQuery("select event.* from event where event_date>=\"" + dateString + "\" and event_date<=\"" + endDateString + "\"");
    }

    public ResultSet getEventFrom(LocalDate date) {
        dateString = LocalDateStringConverter.toString(date);
        return database.executeSelectQuery("select event.* from event where event_date=\"" + dateString + "\";");
    }

    @Override
    public int getRecordId(StoreableInDatabase event) throws IllegalArgumentException {
        if (event instanceof Event) {
            getQueryParam((Event) event);
            int returnValue = -1;
            try {
                ResultSet rs = database.executeSelectQuery("select e.id_event from event e where event_name=\"" + eventName + "\" and event_description=\"" + eventDescription + "\" and event_date=\"" + dateString + "\" and " +
                        "event_end_date=\"" + endDateString + "\" and event_time=\"" + timeString + "\" and event_end_time=\"" + endTimeString + "\" and id_event_type=" + eventTypeId + " limit 1");
                returnValue = rs.getInt("id_event");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return returnValue;
        } else
            throw new IllegalArgumentException("Invalid Event object!");
    }

    @Override
    public ResultSet getRecord(int eventId) {
        return database.executeSelectQuery("select event.* from event where id_event=" + eventId);
    }

}
