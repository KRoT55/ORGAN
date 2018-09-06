package goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type.event_type_list;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.collections.EventTypeCollection;
import goncharenko.organizer.database_class.EventType;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.misc.AbstractObservable;
import goncharenko.organizer.misc.Observer;
import goncharenko.organizer.organizer_database.EventTypeCollectionWithDownloader;
import goncharenko.organizer.organizer_database_data_exchange.EventTypeDatabaseDataExchange;
import goncharenko.organizer.settings.Settings;
import goncharenko.organizer.settings.SettingsLoader;



public class EventTypeListData extends AbstractObservable{

    private OrganizerDatabase database;
    private Settings settings;

    public EventTypeListData(OrganizerDatabase database,Settings settings) {

        this.database = database;
        this.settings=settings;
    }

    VBox getEventTypeLabelList() {
        VBox vbox = new VBox();
        EventTypeCollectionWithDownloader typeDownloader = new EventTypeCollectionWithDownloader(database);
        EventTypeCollection typeCollection = typeDownloader.getAll();
        for (Object type : typeCollection.getSet()) {
            Label label = new Label();
            EventType eventType = (EventType) type;
            label.setText(eventType.getEventTypeName());
           vbox.getChildren().add(produceEventTypeListElement(label,eventType));
        }
        return vbox;
    }

    private HBox produceEventTypeListElement(Label label, EventType eventType) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.getChildren().addAll(label, produceRemoveTypeButton(eventType));
        return hBox;
    }

    private Button produceRemoveTypeButton(EventType eventType) {
        Button button = new Button();
        IMainLanguageHeader languageHeader = settings.getMainLanguageHeader();
        button.setText(languageHeader.getRemoveEventTypeButtonHeader());
        button.setOnAction(e -> {
            EventTypeDatabaseDataExchange typeDBExchange = new EventTypeDatabaseDataExchange(database);
            typeDBExchange.deleteRecord(eventType);
            notifyObservers();
        });
        return button;
    }

    public void notifyObservers(){
        for(Observer observer:getObserverList()){
            if(observer instanceof RemoveEventTypeObserver){
                ((RemoveEventTypeObserver) observer).updateRemoveEventTypeObserver();
            }
        }
    }
}
