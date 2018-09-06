package goncharenko.organizer;

import goncharenko.organizer.database_class.EventType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToEventTypeConverter {
    private static String eventTypeName;
    private static int eventTypeColorId;

    private static void getEventTypeParam(ResultSet rs){
        try {
            eventTypeName = rs.getString("event_type_name");
            eventTypeColorId=rs.getInt("id_event_type_color");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static EventType toEventType(ResultSet resultSet){
        getEventTypeParam(resultSet);
        return new EventType(eventTypeName,eventTypeColorId);
    }
}
