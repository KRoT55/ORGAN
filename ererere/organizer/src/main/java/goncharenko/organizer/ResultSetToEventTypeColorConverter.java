package goncharenko.organizer;

import goncharenko.organizer.database_class.EventTypeColor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToEventTypeColorConverter {

    private static String colorName;
    private static String colorValue;

    public static EventTypeColor toEventTypeColor(ResultSet resultSet){
        getEventTypeColorParam(resultSet);
        return new EventTypeColor(colorName,colorValue);
    }

    private static void getEventTypeColorParam(ResultSet resultSet){
        try {
            colorName = resultSet.getString("event_type_color_name");
            colorValue = resultSet.getString("event_type_color_value");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
