package goncharenko.organizer;

import goncharenko.organizer.collections.EventTypeColorCollection;
import goncharenko.organizer.database_class.EventTypeColor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToEventTypeColorCollectionConverter {

    public static EventTypeColorCollection toCollection(ResultSet resultSet){
        EventTypeColorCollection colorCollection = new EventTypeColorCollection();
        try {
            while (resultSet.next()) {
                EventTypeColor color = ResultSetToEventTypeColorConverter.toEventTypeColor(resultSet);
                colorCollection.add(color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colorCollection;
    }
}
