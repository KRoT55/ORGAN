package goncharenko.organizer.collections;

import goncharenko.organizer.database_class.EventType;

import java.util.TreeSet;

public class EventTypeCollection {

    private TreeSet<EventType> collection;

    public EventTypeCollection() {

        collection = new TreeSet<>();
    }

    public void add(EventType e) {

        collection.add(e);
    }

    public void remove(EventType e) {

        collection.remove(e);
    }

    public void update(EventType oldEventType, EventType updatedEventType) {
        collection.remove(oldEventType);
        collection.add(updatedEventType);
    }

    public TreeSet getSet() {
        return collection;
    }
}
