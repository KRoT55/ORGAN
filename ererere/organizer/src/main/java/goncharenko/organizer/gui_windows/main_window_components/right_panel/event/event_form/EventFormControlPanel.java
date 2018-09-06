package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.database_class.event.EventDate;
import goncharenko.organizer.database_class.event.EventName;
import goncharenko.organizer.database_class.event.EventTime;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.organizer_database_data_exchange.EventDatabaseDataExchange;
import goncharenko.organizer.organizer_database_data_exchange.EventTypeDatabaseDataExchange;
import goncharenko.organizer.settings.Settings;

import java.util.ArrayList;

//add strategy to submit form
class EventFormControlPanel {

    private Button submitFormButton = new Button();
    private Button clearFormButton = new Button();
    private Button closeFormButton = new Button();
    private VBox controlPaneVBox = new VBox();
    private Settings settings;
    private EventFormFields formFields;
    private EventDatabaseDataExchange dbDataExchange;
    private int chosenEventId;
    private ArrayList<EventFormObserver> observers;
    private OrganizerDatabase database;
    private FormControlStrategy formControlStrategy;

    public EventFormControlPanel(EventFormFields formField, OrganizerDatabase database,Settings settings) {

        this.settings=settings;

        this.database = database;

        this.formFields = formField;
        dbDataExchange = new EventDatabaseDataExchange(database);
        setButtonHeaders();
        addButtonHandlers();
        addButtonToVBox();

        observers = new ArrayList<>();

//        formControlStrategy = new AddNewEventStrategy(dbDataExchange);
    }

    private void setButtonHeaders() {
        IMainLanguageHeader languageHeader = settings.getMainLanguageHeader();
        clearFormButton.setText(languageHeader.getClearFormButtonHeader());
        submitFormButton.setText(languageHeader.getSubmitAddEventButtonHeader());
        closeFormButton.setText(languageHeader.getCancelButtonHeader());
    }

    private void addButtonHandlers() {
        addSubmitButtonHandler();
        addClearButtonHandler();
        addCloseButtonHandler();
    }

//    public void updateEventFormAction(EventManagmentAction action) {
//        this.action = action;
//    }

    public void updateEventFormAction(int eventId) {
        chosenEventId = eventId;
    }

    public void setSubmitStrategy(FormControlStrategy controlStrategy) {
        this.formControlStrategy = controlStrategy;
    }

    private void addSubmitButtonHandler() {
        submitFormButton.setOnAction((e) -> {
            //todo add event type choose
            EventFormData data = new EventFormDataExchange(formFields).fetchFormData();
            EventName eventName = new EventName(data.getEventName(), data.getEventDescription());
            EventDate eventDate = new EventDate(data.getEventBeginDate(), data.getEventEndDate());
            EventTime eventTime = new EventTime(data.getEventBeginTime(), data.getEventEndTime());
            EventTypeDatabaseDataExchange typeDb = new EventTypeDatabaseDataExchange(database);
            int eventTypeId = typeDb.getRecordIdByName(data.getEventTypeName());

            Event event = new Event(eventName, eventDate, eventTime, eventTypeId);

            if(formControlStrategy instanceof UpdateEventStrategy)
                ((UpdateEventStrategy) formControlStrategy).setChosenEventId(chosenEventId);
            formControlStrategy.handleSubmitAction(event);

            closeFormButton.fire();
        });
    }

    private void addClearButtonHandler() {
        clearFormButton.setOnAction((e) -> {
            formFields.clearAllField();
        });
    }

    private void addCloseButtonHandler() {
        closeFormButton.setOnAction((e) -> {
            notifyObservers();
        });
    }

    private void addButtonToVBox() {
        HBox controlPaneHBox = new HBox();
        controlPaneHBox.getChildren().addAll(clearFormButton, submitFormButton);
        controlPaneVBox.getChildren().addAll(controlPaneHBox, closeFormButton);
        controlPaneHBox.setPadding(new Insets(3));
    }

    VBox getPanelAsVBoxNode() {
        return controlPaneVBox;
    }

    void registerObserver(EventFormObserver observer) {
        observers.add(observer);
    }

    void removeObserver(EventFormObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (EventFormObserver observer : observers) {
            observer.update();
        }
    }

    void updateSubmitButtonHeader(String header) {
        submitFormButton.setText(header);
    }
}
