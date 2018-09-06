package goncharenko.organizer.organizer_database;

import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.ResultSetToEventTypeConverter;
import goncharenko.organizer.collections.EventTypeCollection;
import goncharenko.organizer.database_class.EventType;
import goncharenko.organizer.organizer_database_data_exchange.EventTypeDatabaseDataExchange;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventTypeCollectionWithDownloader {
    private EventTypeCollection eventTypeCollection;
    private EventTypeDatabaseDataExchange dataExchange;

    public EventTypeCollectionWithDownloader(OrganizerDatabase database) {
        eventTypeCollection = new EventTypeCollection();
        dataExchange = new EventTypeDatabaseDataExchange(database);
    }

    public EventTypeCollection getAll() {
        ResultSet rs = dataExchange.getAllRecords();
        try {
            while (rs.next()) {
                EventType eventType = ResultSetToEventTypeConverter.toEventType(rs);
                eventTypeCollection.add(eventType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventTypeCollection;
    }
}
