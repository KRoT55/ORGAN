package goncharenko.organizer.organizer_database_data_exchange;

import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.database_class.EventType;
import goncharenko.organizer.database_class.StoreableInDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventTypeDatabaseDataExchange implements DatabaseDownloadable {
    private String eventTypeName;
    private OrganizerDatabase database;
    private int eventTypeColorId;

    public EventTypeDatabaseDataExchange(OrganizerDatabase database) {
        this.database = database;
    }

    public void updateRecord(int itemId, StoreableInDatabase object) {
        if (object instanceof EventType) {
            getQueryParam((EventType) object);

            database.executeUpdateQuery("updateTypeList event_type set event_type_name=\"" + eventTypeName + "\", id_event_type_color=" + eventTypeColorId + " where id_event_type=" + itemId);
        } else throw new IllegalArgumentException("Invalid EventType object!");
    }

    public void deleteRecord(int itemId) {
        database.executeDeleteQuery("delete from event_type where id_event_type=" + itemId);
    }

    public void addRecord(StoreableInDatabase object) {
        if (object instanceof EventType) {
            getQueryParam((EventType) object);
            database.executeInsertQuery("insert into event_type(event_type_name,id_event_type_color) values(\"" + eventTypeName + "\"," + eventTypeColorId + ")");
        } else throw new IllegalArgumentException("Invalid EventType object!");
    }

    public ResultSet getAllRecords() {

        return database.executeSelectQuery("select * from event_type;");

    }

    public int getRecordId(StoreableInDatabase object) {
        if (object instanceof EventType) {
            int typeId = 0;
            getQueryParam((EventType) object);
            ResultSet rs = database.executeSelectQuery("select id_event_type from event_type where event_type_name=\"" + eventTypeName + "\"");
            try {
                typeId = rs.getInt("id_event_type");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return typeId;
        } else throw new IllegalArgumentException("Invalid EventType object!");
    }

    public ResultSet getRecord(int itemId) {
        return database.executeSelectQuery("select * from event_type where id_event_type=" + itemId);
    }

    public int getRecordIdByName(String typeName) {
        int typeId=0;
        try {
            typeId = database.executeSelectQuery("select id_event_type from event_type where event_type_name=\"" + typeName+"\"").getInt("id_event_type");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return typeId;
    }

    private void getQueryParam(EventType eventTypeObject) {
        eventTypeName = eventTypeObject.getEventTypeName();
        eventTypeColorId = eventTypeObject.getEventTypeColorId();
    }

    public int getColorId(int eventTypeId) {
        int colorId = 0;
        try {
            ResultSet rs = database.executeSelectQuery("select id_event_type_color from event_type where id_event_type=" + eventTypeId);
            colorId = rs.getInt("id_event_type_color");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colorId;
    }

    public void deleteRecord(StoreableInDatabase object){
        int eventId = getRecordId(object);
        deleteRecord(eventId);
    }
}
