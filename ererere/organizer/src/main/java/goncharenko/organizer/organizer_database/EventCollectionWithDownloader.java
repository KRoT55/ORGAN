package goncharenko.organizer.organizer_database;

import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.ResultSetToEventCollectionConverter;
import goncharenko.organizer.ResultSetToEventConverter;
import goncharenko.organizer.collections.EventCollection;
import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.organizer_database_data_exchange.EventDatabaseDataExchange;

import java.sql.ResultSet;
import java.time.LocalDate;


public class EventCollectionWithDownloader {
    private EventDatabaseDataExchange eventDownloader;
    private EventCollection eventCollection;

    public EventCollectionWithDownloader(OrganizerDatabase database) {
        eventDownloader = new EventDatabaseDataExchange(database);
        eventCollection = new EventCollection(20);
    }

    public EventCollection getEventCollectionBetween(LocalDate from, LocalDate to) {
        ResultSet rs = eventDownloader.getEventBetween(from, to);
        try {
            while (rs.next()) {
                Event event = ResultSetToEventConverter.toEventObject(rs);
                eventCollection.add(event);
            }
        } catch (Exception e) {
        }
        return eventCollection;
    }

    public EventCollection getEventCollectionFrom(LocalDate date){
        ResultSet rs = eventDownloader.getEventFrom(date);
        return ResultSetToEventCollectionConverter.toEventCollection(rs);
    }
}
