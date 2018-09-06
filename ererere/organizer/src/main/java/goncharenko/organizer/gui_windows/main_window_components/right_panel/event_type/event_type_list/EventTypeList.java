package goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type.event_type_list;

import javafx.scene.layout.VBox;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.DisplayableInRightPanel;
import goncharenko.organizer.settings.Settings;


public class EventTypeList implements DisplayableInRightPanel {

    private VBox vBox;
    private EventTypeListControlPanel controlPanel;
    private OrganizerDatabase database;
    private EventTypeListData eventTypeListData;

    public EventTypeList(VBox vBox, OrganizerDatabase database, Settings settings) {
        this.vBox = vBox;
        this.database = database;
        controlPanel = new EventTypeListControlPanel(settings);
        eventTypeListData = new EventTypeListData(database,settings);
    }

    public void display() {
        controlPanel.addPanelToRightPanel(vBox);
        vBox.getChildren().add(getEventTypeList());

    }

    private VBox getEventTypeList() {
        return eventTypeListData.getEventTypeLabelList();
    }


    public void registerObserver(EventTypeListObserver observer) {
        controlPanel.registerObserver(observer);
    }

    public void registerObserverToRemoveEvent(RemoveEventTypeObserver observer){
        eventTypeListData.registerObserver(observer);
    }
}

