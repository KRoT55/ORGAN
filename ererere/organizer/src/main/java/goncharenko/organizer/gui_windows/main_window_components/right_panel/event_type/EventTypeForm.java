package goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.DisplayableInRightPanel;
import goncharenko.organizer.settings.Settings;

public class EventTypeForm implements DisplayableInRightPanel {

    private VBox vBox;
    private OrganizerDatabase database;
    private EventTypeFormLabels formLabels;
    private EventTypeFormFields formFields;
    private EventTypeFormControlPanel controlPanel;

    public EventTypeForm(VBox vBox, OrganizerDatabase database, Settings settings) {
        this.vBox = vBox;
        this.database = database;
        formLabels = new EventTypeFormLabels(settings);
        formFields = new EventTypeFormFields(database);
        controlPanel = new EventTypeFormControlPanel(database, settings,formFields);
    }


    public void display() {
        splitComponentsToVBox();
    }

    private void splitComponentsToVBox() {
        Label[] labels = formLabels.getLabels();
        Node[] fields = formFields.getFields();
        vBox.getChildren().addAll(labels[0], fields[0], labels[1], fields[1]);
        vBox.getChildren().addAll(controlPanel.getFormControlPanel());
    }

    public void registerObserver(EventTypeFormObserver observer) {
        controlPanel.registerObserver(observer);
    }


}
