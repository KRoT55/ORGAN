package goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.collections.EventTypeColorCollection;
import goncharenko.organizer.database_class.EventTypeColor;
import goncharenko.organizer.organizer_database.EventTypeColorCollectionWithDownloader;

import java.util.ArrayList;
import java.util.List;

public class EventTypeFormFields {

    private TextField eventNameTextField;
    private ComboBox<String> eventTypeColorComboBox;
    private EventTypeColorCollectionWithDownloader colorCollectionDownloader;

    public EventTypeFormFields(OrganizerDatabase database) {
        eventNameTextField = new TextField();
        eventTypeColorComboBox = new ComboBox<>();

        colorCollectionDownloader = new EventTypeColorCollectionWithDownloader(database);

        fillColorComboBox();
    }

    private List<String> fetchEventTypeColor() {
        EventTypeColorCollection colorCollection = colorCollectionDownloader.getAvailableColors();
        ArrayList<String> colorList = new ArrayList<>();
        for (Object color : colorCollection.getSet()) {
            if (color instanceof EventTypeColor) {
                colorList.add(((EventTypeColor) color).getName());
            }

        }
        return colorList;
    }

     private void fillColorComboBox() {
        eventTypeColorComboBox.getItems().addAll(fetchEventTypeColor());
    }

    String getEventNameText() {
        return eventNameTextField.getText();
    }

    String getEventTypeColor() {
        return eventTypeColorComboBox.getValue();
    }

    Node[] getFields() {
        return new Node[]{eventNameTextField, eventTypeColorComboBox};
    }

    void setEventNameTextField(String eventTypeName) {
        eventNameTextField.setText(eventTypeName);
    }

    void setEventTypeColorComboBox(String colorString) {
        eventTypeColorComboBox.setValue(colorString);
    }

    void clearFields(){
        eventNameTextField.setText("");
        eventTypeColorComboBox.getItems().clear();
        fillColorComboBox();
    }
}
