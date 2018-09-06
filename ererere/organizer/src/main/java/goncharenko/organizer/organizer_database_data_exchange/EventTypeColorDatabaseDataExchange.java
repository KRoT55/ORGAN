package goncharenko.organizer.organizer_database_data_exchange;

import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.database_class.EventTypeColor;
import goncharenko.organizer.database_class.StoreableInDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventTypeColorDatabaseDataExchange implements DatabaseDownloadable {

    private OrganizerDatabase database;
    private String colorName;
    private String colorHexValue;

    public EventTypeColorDatabaseDataExchange(OrganizerDatabase database) {
        this.database = database;
    }

    public void updateRecord(int itemId, StoreableInDatabase object) {
        if (isPassedObjectCorrect(object)) {
            getQueryParam((EventTypeColor) object);

            database.executeUpdateQuery("updateTypeList event_type_color set event_type_color_name=\"" + colorName + "\", event_type_color_value=\"" + colorHexValue + "\" where id_event_type_color=" + itemId);
        } else
            throw new IllegalArgumentException("Invalid EventTypeColor object!");
    }

    public void deleteRecord(int itemId) {
        database.executeDeleteQuery("delete from event_type_color where id_event_type_color=" + itemId);
    }

    public void addRecord(StoreableInDatabase object) {
        if (isPassedObjectCorrect(object)) {
            getQueryParam((EventTypeColor) object);
            database.executeInsertQuery("insert into event_type_color(event_type_color_name, event_type_color_value) values(\"" + colorName + "\",\"" + colorHexValue + "\");");
        } else throw new IllegalArgumentException("Invalid EventTypeColor object!");
    }

    public ResultSet getAllRecords() {
        return database.executeSelectQuery("select * from event_type_color;");
    }

    public int getRecordId(StoreableInDatabase object) {
        if (isPassedObjectCorrect(object)) {
            int eventTypeColorId = 0;
            getQueryParam((EventTypeColor) object);
            try {
                ResultSet rs = database.executeSelectQuery("select id_event_type_color from event_type_color where event_type_color_name=\"" + colorName + "\" ");
                eventTypeColorId = rs.getInt("id_event_type_color");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return eventTypeColorId;
        } else throw new IllegalArgumentException("Invalid EventTypeColor object!");
    }

    public ResultSet getRecord(int itemId) {
        return database.executeSelectQuery("select * from event_type_color where id_event_type_color=" + itemId);
    }

    private void getQueryParam(EventTypeColor object) {
        this.colorName = object.getName();
        this.colorHexValue = object.getHexValue();
    }

    private boolean isPassedObjectCorrect(StoreableInDatabase object) {
        return object instanceof EventTypeColor;
    }

    public int getRecordIdByName(String colorName){
        return getRecordId(new EventTypeColor(colorName, "doesnt matter"));
    }
    public ResultSet getAvailableColors(){
        return database.executeSelectQuery("select * from event_type_color where id_event_type_color not in (select id_event_type_color from event_type)");
    }
}
