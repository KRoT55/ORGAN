package goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.database_class.EventType;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.organizer_database_data_exchange.EventTypeColorDatabaseDataExchange;
import goncharenko.organizer.organizer_database_data_exchange.EventTypeDatabaseDataExchange;
import goncharenko.organizer.settings.Settings;
import goncharenko.organizer.settings.SettingsLoader;

import java.util.ArrayList;

public class EventTypeFormControlPanel {

    private final Settings settings;
    private Button submitNewTypeButton;
    private ArrayList<EventTypeFormObserver> observers;
    private EventTypeDatabaseDataExchange typeDBExchange;
    private EventTypeFormDataExchange typeFormExchange;
    private EventTypeColorDatabaseDataExchange colorDBExchange;
    private EventTypeFormFields formFields;

    public EventTypeFormControlPanel(OrganizerDatabase database,Settings settings,EventTypeFormFields formFields){
        submitNewTypeButton=new Button();
        observers=new ArrayList<>();
        typeDBExchange=new EventTypeDatabaseDataExchange(database);
        typeFormExchange = new EventTypeFormDataExchange(formFields);
        colorDBExchange = new EventTypeColorDatabaseDataExchange(database);
        this.formFields=formFields;

        setSubmitNewTypeButtonHandler();
        this.settings=settings;
        setButtonHeaders(settings.getMainLanguageHeader());
    }

    private void setButtonHeaders(IMainLanguageHeader languageHeader){
        submitNewTypeButton.setText(languageHeader.getNewEventTypeButtonHeader());
    }

    public void updateButtonHeaders(IMainLanguageHeader languageHeader){
        setButtonHeaders(languageHeader);
    }

    private void notifyObservers(){
        for(EventTypeFormObserver observer:observers){
            observer.updateEventTypeFormObserver();
        }
    }

    void registerObserver(EventTypeFormObserver observer){
        observers.add(observer);
    }
    private void setSubmitNewTypeButtonHandler(){
        submitNewTypeButton.setOnAction(e->{

            EventTypeFormData formData = typeFormExchange.fetchData();
            EventType eventType = new EventType(formData.getEventTypeName(),colorDBExchange.getRecordIdByName(formData.getEventTypeColorName()));
            typeDBExchange.addRecord(eventType);
            formFields.clearFields();
            notifyObservers();
        });
    }

    HBox getFormControlPanel(){
        HBox hBox =new HBox();
        hBox.getChildren().addAll(submitNewTypeButton);
        return hBox;
    }
}
