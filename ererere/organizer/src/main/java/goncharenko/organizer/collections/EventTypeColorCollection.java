package goncharenko.organizer.collections;

import goncharenko.organizer.database_class.EventTypeColor;

import java.util.TreeSet;

public class EventTypeColorCollection {

    private TreeSet<EventTypeColor> collection;

    public EventTypeColorCollection() {

        collection = new TreeSet<>();
    }

    public void add(EventTypeColor e) {

        collection.add(e);
    }

    public void remove(EventTypeColor e) {
        collection.remove(e);
    }

    public void update(EventTypeColor oldEventType, EventTypeColor updatedEventType) {
        collection.remove(oldEventType);
        collection.add(updatedEventType);
    }

    public TreeSet getSet() {
        return collection;
    }
}
