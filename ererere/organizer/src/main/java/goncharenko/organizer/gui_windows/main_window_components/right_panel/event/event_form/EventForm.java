package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.collections.EventTypeCollection;
import goncharenko.organizer.database_class.EventType;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.DisplayableInRightPanel;
import goncharenko.organizer.organizer_database.EventTypeCollectionWithDownloader;
import goncharenko.organizer.organizer_database.EventTypeColorCollectionWithDownloader;
import goncharenko.organizer.settings.Settings;

public class EventForm implements DisplayableInRightPanel {

    protected VBox eventFormRightPanelVBox;
    private VBox formComponentsVBox = new VBox();
    private Label formTitleLabel = new Label();

    private EventFormLabels eventFormLabels;
    private EventFormFields eventFormFields;
    private EventFormControlPanel formControlPanel;
    private EventFormDataExchange formDataExchange;
    protected OrganizerDatabase database;

    private Settings settings;

    public EventForm(VBox vBox, OrganizerDatabase database, Settings settings) {

        eventFormRightPanelVBox = vBox;
        this.eventFormFields = new EventFormFields();
        this.eventFormLabels = new EventFormLabels(settings);
        formControlPanel = new EventFormControlPanel(eventFormFields, database, settings);
        formDataExchange = new EventFormDataExchange(eventFormFields);
        this.database = database;
        this.settings = settings;

    }

    //copy constructor
    public EventForm(EventForm eventForm) {
        this.eventFormRightPanelVBox = eventForm.eventFormRightPanelVBox;
        this.formComponentsVBox = eventForm.formComponentsVBox;
        this.formTitleLabel = eventForm.formTitleLabel;

        this.eventFormLabels = eventForm.eventFormLabels;
        this.formDataExchange = eventForm.formDataExchange;
        this.database = eventForm.database;

        this.settings = eventForm.settings;
        this.formControlPanel = eventForm.formControlPanel;
        this.eventFormFields = eventForm.eventFormFields;
    }

    public void display() {
        formTitleLabel.setText(settings.getMainLanguageHeader().getFormTitleHeader());
        fillEventTypeComboBox();

        addComponentsToFormVBox();
        splitAllComponentsIntoMainVBox();
    }

    public void fillEventFormWith(EventFormData eventFormData) {
        formDataExchange.fillEventFormWith(eventFormData);
    }

    public EventFormControlPanel getEventFormControlPanel() {
        return formControlPanel;
    }

    private void addComponentsToFormVBox() {
        if (!formComponentsVBox.getChildren().isEmpty())
            formComponentsVBox.getChildren().clear();

        formComponentsVBox.getChildren().addAll(eventFormLabels.getEventNameLabel(), eventFormFields.getEventNameField(),
                eventFormLabels.getEventDescLabelLabel(), eventFormFields.getEventDescArea(),
                eventFormLabels.getEventDateLabel(), eventFormFields.getEventDateField(),
                eventFormLabels.getEventEndDateLabel(), eventFormFields.getEventEndDateField(),
                eventFormLabels.getEventTimeLabel(), eventFormFields.getEventTimeField(),
                eventFormLabels.getEventEndTimeLabel(), eventFormFields.getEventEndTimeField(),
                eventFormLabels.getEventTypeLabel(), eventFormFields.getEventTypeComboBox());
    }

    private void splitAllComponentsIntoMainVBox() {
        this.eventFormRightPanelVBox.getChildren().addAll(formTitleLabel, formComponentsVBox, formControlPanel.getPanelAsVBoxNode());
    }

    public void registerObserver(EventFormObserver observer) {
        formControlPanel.registerObserver(observer);
    }

    public void removeObserver(EventFormObserver observer) {
        formControlPanel.removeObserver(observer);
    }

    public void updateEventFormAction(int eventId) {
        formControlPanel.updateEventFormAction(eventId);
    }

    private void fillEventTypeComboBox() {
        EventTypeCollectionWithDownloader typeCollection = new EventTypeCollectionWithDownloader(database);
        EventTypeColorCollectionWithDownloader colorCollection = new EventTypeColorCollectionWithDownloader(database);

        ObservableList<String> eventType = FXCollections.observableArrayList();
        ComboBox<String> comboBox = eventFormFields.getEventTypeComboBox();
        comboBox.setItems(eventType);
        EventTypeCollection collection = typeCollection.getAll();
        for (Object object : collection.getSet()) {
            EventType type = (EventType) object;
            comboBox.getItems().add(type.getEventTypeName());
        }
    }

    public void setControlSubmitStrategy(FormControlStrategy controlStrategy) {
        formControlPanel.setSubmitStrategy(controlStrategy);
    }

    public void updateSubmitButtonHeader(String headerString) {
        formControlPanel.updateSubmitButtonHeader(headerString);
    }
}