package goncharenko.organizer;

import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.database_class.event.EventDate;
import goncharenko.organizer.database_class.event.EventName;
import goncharenko.organizer.database_class.event.EventTime;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToEventConverter {

    private static String name;
    private static String desc;
    private static String beginDate;
    private static String endDate;
    private static String beginTime;
    private static String endTime;
    private static int eventTypeID;
    private static int eventId;

    public ResultSetToEventConverter() {
        name = "";
        desc = "";
        beginDate = "";
        endDate = "";
        eventTypeID = 0;
        eventId = 0;
    }

    public static Event toEventObject(ResultSet resultSetFromEventQuery) {
        getEventDataFromResultSet(resultSetFromEventQuery);
        EventName eventName = new EventName(name, desc);
        EventDate eventDate = new EventDate(beginDate, endDate);
        EventTime eventTime = new EventTime(beginTime, endTime);
        return new Event(eventName, eventDate, eventTime, eventTypeID);
    }

    private static void getEventDataFromResultSet(ResultSet rs) {
        try {
            name = rs.getString("event_name");
            desc = rs.getString("event_description");
            beginDate = rs.getString("event_date");
            endDate = rs.getString("event_end_date");
            beginTime = rs.getString("event_time");
            endTime = rs.getString("event_end_time");
            eventTypeID = rs.getInt("id_event_type");
            eventId = rs.getInt("id_event");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
