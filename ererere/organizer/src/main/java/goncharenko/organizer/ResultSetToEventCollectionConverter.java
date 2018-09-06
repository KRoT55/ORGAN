package goncharenko.organizer;

import goncharenko.organizer.collections.EventCollection;
import goncharenko.organizer.database_class.event.Event;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToEventCollectionConverter {

    public static EventCollection toEventCollection(ResultSet rs){
        EventCollection collection = new EventCollection(5);
        try {
            while (rs.next()) {
                Event event = ResultSetToEventConverter.toEventObject(rs);
                collection.add(event);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return collection;
    }
}
