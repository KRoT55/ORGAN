package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_list;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.collections.EventCollection;
import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.organizer_database_data_exchange.EventDatabaseDataExchange;

import java.time.LocalDate;
import java.util.*;

public class EventListData {

    private TreeMap<Button, Integer> eventList;
    private EventDatabaseDataExchange dbExchange;
    private ArrayList<EventListObserver> observers;

    public EventListData(OrganizerDatabase database) {
        eventList = new TreeMap<>(new Comparator<Button>() {
            @Override
            public int compare(Button o1, Button o2) {
                return o1.getText().compareTo(o2.getText());
            }
        });
        dbExchange = new EventDatabaseDataExchange(database);
        observers = new ArrayList<>();
    }

    public TreeMap<Button, Integer> getEventList(EventCollection eventCollection, LocalDate date) {
        if (!this.eventList.isEmpty())
            clearEventList();

            addEventsToList(eventCollection, date);

        return eventList;
    }

    private void clearEventList() {
        this.eventList.clear();
    }

    private void addEventsToList(EventCollection eventCollection, LocalDate date) {
        Collection collection = eventCollection.getEventSet(date);
        Iterator eventIterator = collection.iterator();
        while (eventIterator.hasNext()) {
            Event event = (Event) eventIterator.next();
            Button button = produceEventListButton();
            setButtonText(button, event);
            int eventId = getEventId(event);
            eventList.put(button, eventId);
        }
    }

    private Button produceEventListButton() {
        Button button = new Button();
        button.setOnAction(e -> notifyObservers((Button) e.getSource()));
        button.setAlignment(Pos.BASELINE_LEFT);
        return button;
    }

    private void setButtonText(Button button, Event event) {
        button.setText(event.getEventTime() + " - " + event.getEventEndTime() + "\n" + event.getName().getEventName() + "\n"
                + event.getName().getEventDescription() + "\n" + event.getBeginDate() + "\t" + event.getEndDate());

    }



    private int getEventId(Event event) {
        return dbExchange.getRecordId(event);
    }

    int getEventId(Button button) {
        return eventList.get(button);
    }

    void registerObserver(EventListObserver observer) {
        observers.add(observer);
    }

    void removeObserver(EventListObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Button button) {
        for (EventListObserver observer : observers) {
            observer.update(button);
        }
    }
}
