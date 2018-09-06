package goncharenko.organizer.collections;

import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.database_class.event.EventDate;

import java.time.LocalDate;
import java.util.*;


public class EventCollection {
    private Map<LocalDate, TreeSet<Event>> collection;
    private TreeSet<Event> temporarySet;

    public EventCollection(int initialCapacity) {
        collection = new HashMap<>(initialCapacity);
    }

    private void add(LocalDate date, Event e) {
        temporarySet = getEventSet(date);
        temporarySet.add(e);
    }
    public void add(Event e){
        add(e.getBeginDate(),e);
    }

    public TreeSet getEventSet(LocalDate date) {

        if (collection.get(date) == null) {
            collection.put(date, new TreeSet<>());
        }
        return collection.get(date);
    }

    public void remove(LocalDate date, Event e) {
        temporarySet = getEventSet(date);
        temporarySet.remove(e);
    }

    public void update(LocalDate date, Event oldEvent, Event updatedEvent) {
        temporarySet = getEventSet(date);
        temporarySet.remove(oldEvent);
        temporarySet.add(updatedEvent);
    }

    public Iterator getCollectionIterator() {
        return collection.entrySet().iterator();
    }
}
