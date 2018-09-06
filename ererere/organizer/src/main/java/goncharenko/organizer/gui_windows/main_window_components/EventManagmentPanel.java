package goncharenko.organizer.gui_windows.main_window_components;

import javafx.scene.control.Button;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.EventManagmentHandler;
import goncharenko.organizer.organizer_database_data_exchange.EventDatabaseDataExchange;

public class EventManagmentPanel {

    private Button addNewEventButton;
    private Button changeEventButton;
    private Button removeEventButton;

    private EventManagmentHandler eventManagmentHandler;


    public EventManagmentPanel(Button addNewEventButton, Button changeEventButton, Button removeEventButton) {
        this.addNewEventButton = addNewEventButton;
        this.changeEventButton = changeEventButton;
        this.removeEventButton = removeEventButton;
        eventManagmentHandler = EventManagmentHandler.getInstance();
        addButtonListeners();
    }

    public void setDisableChangeButton(boolean value) {
        this.changeEventButton.setDisable(value);
    }

    public void setDisableRemoveButton(boolean value) {
        this.removeEventButton.setDisable(value);
    }

    private void addButtonListeners() {

        addNewEventButtonListener();
        addChangeEventButtonListener();
        addRemoveButtonListener();
    }

    private void addNewEventButtonListener() {
        addNewEventButton.setOnAction((e) -> {
            eventManagmentHandler.notifyListeners(EventManagmentAction.NEW_EVENT);
        });
    }

    private void addChangeEventButtonListener() {
        changeEventButton.setOnAction(e -> {
            setDisableChangeButton(true);
            eventManagmentHandler.notifyListeners(EventManagmentAction.UPDATE_EVENT);

        });
    }

    private void addRemoveButtonListener() {
        removeEventButton.setOnAction(e -> {
            setDisableRemoveButton(true);
            eventManagmentHandler.notifyListeners(EventManagmentAction.REMOVE_EVENT);
        });
    }

}
