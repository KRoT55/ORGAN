package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.organizer_database_data_exchange.EventDatabaseDataExchange;

 public abstract class FormControlStrategy {
    protected EventDatabaseDataExchange databaseDataExchange;

    public FormControlStrategy(EventDatabaseDataExchange dataExchange) {
        this.databaseDataExchange = dataExchange;
    }

    abstract void handleSubmitAction(Event fetchedEvent);
}
