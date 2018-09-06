package goncharenko.organizer.organizer_database;

import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.ResultSetToEventTypeColorCollectionConverter;
import goncharenko.organizer.ResultSetToEventTypeColorConverter;
import goncharenko.organizer.ResultSetToEventTypeConverter;
import goncharenko.organizer.collections.EventTypeColorCollection;
import goncharenko.organizer.database_class.EventTypeColor;
import goncharenko.organizer.organizer_database_data_exchange.EventTypeColorDatabaseDataExchange;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventTypeColorCollectionWithDownloader {

    private EventTypeColorCollection colorCollection;
    private EventTypeColorDatabaseDataExchange dataExchange;

    public EventTypeColorCollectionWithDownloader(OrganizerDatabase database) {
        colorCollection = new EventTypeColorCollection();
        dataExchange = new EventTypeColorDatabaseDataExchange(database);
    }

    public EventTypeColorCollection getAllColors() {
        ResultSet resultSet = dataExchange.getAllRecords();
        return convertToColorCollection(resultSet);
    }

    public EventTypeColorCollection getAvailableColors() {
        ResultSet resultSet = dataExchange.getAvailableColors();
        return convertToColorCollection(resultSet);
    }

    private EventTypeColorCollection convertToColorCollection(ResultSet resultSet) {
        return ResultSetToEventTypeColorCollectionConverter.toCollection(resultSet);
    }
}
