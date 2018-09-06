package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_list;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.DisplayableInRightPanel;
import goncharenko.organizer.organizer_database.EventCollectionWithDownloader;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

public class EventList implements DisplayableInRightPanel {

    private EventCollectionWithDownloader eventDownloader;
    private LocalDate date;
    private VBox vBox;
    private EventListData eventListData;

    public EventList(VBox vBox, OrganizerDatabase database) {
        this.vBox = vBox;
        this.eventDownloader = new EventCollectionWithDownloader(database);

        eventListData = new EventListData(database);
    }

    public void display() throws IllegalArgumentException {
        if (date == null)
            throw new IllegalArgumentException("Date not set!");

        Map eventList = eventListData.getEventList(eventDownloader.getEventCollectionFrom(date), date);
        if(eventList.entrySet().isEmpty())
            addNoneEventLabel();
        else {
            Iterator eventListButtonIterator = eventList.keySet().iterator();
            while (eventListButtonIterator.hasNext()) {
                Button button = (Button) eventListButtonIterator.next();
                vBox.getChildren().add(button);
                vBox.setMargin(button, new Insets(5, 0, 0, 5));
            }
        }
    }
    private void addNoneEventLabel(){
        Label label = new Label();
        label.setText("There are no events for this day.");
        vBox.getChildren().add(label);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getEventIdFromListButton(Button button) {
        return eventListData.getEventId(button);
    }

    public void registerObserver(EventListObserver observer) {
        eventListData.registerObserver(observer);
    }

    public void removeObserver(EventListObserver observer) {
        eventListData.removeObserver(observer);
    }


}
