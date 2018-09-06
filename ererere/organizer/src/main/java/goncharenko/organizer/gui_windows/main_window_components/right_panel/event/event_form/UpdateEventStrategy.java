package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.organizer_database_data_exchange.EventDatabaseDataExchange;

public class UpdateEventStrategy extends FormControlStrategy {

    private int chosenEventId;

    public UpdateEventStrategy( EventDatabaseDataExchange eventDataExchange){
        super(eventDataExchange);
    }


     void handleSubmitAction(Event fetchedEvent){
         this.databaseDataExchange.updateRecord(chosenEventId,fetchedEvent);
     }

    public void setChosenEventId(int eventId){
         this.chosenEventId=eventId;
    }
}
