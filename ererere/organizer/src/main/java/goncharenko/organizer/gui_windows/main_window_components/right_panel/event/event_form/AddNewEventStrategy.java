package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.organizer_database_data_exchange.EventDatabaseDataExchange;

public class AddNewEventStrategy extends FormControlStrategy {

    public AddNewEventStrategy(EventDatabaseDataExchange dataExchange) {
        super(dataExchange);
    }


    void handleSubmitAction(Event fetchedEvent){
        this.databaseDataExchange.addRecord(fetchedEvent);
    }
}
