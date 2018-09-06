package goncharenko.organizer.gui_windows.main_window_components.right_panel;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.ResultSetToEventConverter;
import goncharenko.organizer.ResultSetToEventTypeConverter;
import goncharenko.organizer.database_class.EventType;
import goncharenko.organizer.database_class.event.Event;
import goncharenko.organizer.gui_windows.main_window_components.CalendarDayHandler;
import goncharenko.organizer.gui_windows.main_window_components.CalendarListener;
import goncharenko.organizer.gui_windows.main_window_components.EventManagmentAction;
import goncharenko.organizer.gui_windows.main_window_components.EventManagmentPanel;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form.*;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_list.EventList;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_list.EventListObserver;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type.EventTypeForm;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type.EventTypeFormObserver;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type.event_type_list.EventTypeList;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type.event_type_list.EventTypeListObserver;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type.event_type_list.RemoveEventTypeObserver;
import goncharenko.organizer.organizer_database_data_exchange.EventDatabaseDataExchange;
import goncharenko.organizer.organizer_database_data_exchange.EventTypeDatabaseDataExchange;
import goncharenko.organizer.settings.Settings;

import java.sql.ResultSet;
import java.time.LocalDate;

public class RightPanel implements EventListObserver, EventFormObserver, CalendarListener, EventManagmentListener, EventTypeListObserver,EventTypeFormObserver,RemoveEventTypeObserver {

    private Label panelTitle;
    private RightPanelContent rightPanelContent;
    private EventManagmentPanel eventManagmentPanel;
    private AnchorPane anchorPane;
    private VBox vBox;
    private CalendarDayHandler dayHandler;
    private EventManagmentHandler eventManagmentHandler;
    private Button chosenEventListButton;
    private EventDatabaseDataExchange dbExchange;
    private EventList eventList;
    private EventForm eventForm;
    private OrganizerDatabase database;
    private EventTypeList eventTypeList;
    private EventTypeForm eventTypeForm;
    private Settings settings;

    public RightPanel(Label panelTitle, AnchorPane anchorPane, EventManagmentPanel eventManagmentPanel, OrganizerDatabase database,Settings settings) {
        this.panelTitle = panelTitle;
        this.anchorPane = anchorPane;
        this.eventManagmentPanel = eventManagmentPanel;
        this.vBox = new VBox();
        this.database = database;
        this.settings=settings;

        dbExchange = new EventDatabaseDataExchange(database);
        eventList = new EventList(vBox, database);
        eventList.registerObserver(this);
        eventForm = new EventForm(vBox, database,settings);
        eventForm.registerObserver(this);
        eventTypeList = new EventTypeList(vBox, database,settings);
        eventTypeList.registerObserver(this);
        eventTypeList.registerObserverToRemoveEvent(this);
        eventTypeForm = new EventTypeForm(vBox, database,settings);
        eventTypeForm.registerObserver(this);

        registerToCalendarHandler();
        registerToEventManagmentHandler();
        initRightPanelContent();
        initialDisplayTodayEvents();
    }

    private void initRightPanelContent() {
        this.anchorPane.getChildren().add(vBox);
        this.rightPanelContent = new RightPanelContent(vBox);
    }

    private void registerToCalendarHandler() {
        dayHandler = CalendarDayHandler.getInstance();
        dayHandler.registerNewListener(this);
    }

    private void registerToEventManagmentHandler() {
        eventManagmentHandler = EventManagmentHandler.getInstance();
        eventManagmentHandler.registerNewListener(this);
    }

    public void updateCalendarListener(LocalDate date) {
        eventList.setDate(date);
        rightPanelContent.displayContent(eventList);
    }
    private void initialDisplayTodayEvents(){
        update();
    }

    @Override
    public void update(Button button) {
        eventManagmentPanel.setDisableRemoveButton(false);
        eventManagmentPanel.setDisableChangeButton(false);
        chosenEventListButton = button;
    }

    @Override
    public void update() {
        eventList.setDate(LocalDate.now());
        rightPanelContent.displayContent(eventList);
    }

    @Override
    public void updateTypeList() {
        rightPanelContent.displayContent(eventTypeForm);
    }

    @Override
    public void updateEventTypeFormObserver(){
        rightPanelContent.displayContent(eventTypeList);
    }
    @Override
    public void updateRemoveEventTypeObserver(){
        rightPanelContent.displayContent(eventTypeList);
    }

    public void updateEventManagmentListener(EventManagmentAction action) throws IllegalArgumentException {

        switch (action) {
            case NEW_EVENT:
                eventForm.setControlSubmitStrategy(new AddNewEventStrategy(dbExchange));
                rightPanelContent.displayContent(eventForm);
                break;
            case UPDATE_EVENT:
                eventForm.setControlSubmitStrategy(new UpdateEventStrategy(dbExchange));
                              eventForm.updateSubmitButtonHeader(settings.getMainLanguageHeader().getManageEventButtonHeader()[1]);
                eventManagmentPanel.setDisableRemoveButton(true);
                DisplayableInRightPanel currentContent = rightPanelContent.getCurrentDisplayedContent();
                if (currentContent instanceof EventList) {
                    int eventId = ((EventList) currentContent).getEventIdFromListButton(chosenEventListButton);

                    Event event = ResultSetToEventConverter.toEventObject(dbExchange.getRecord(eventId));
                    EventFormData eventFormData = new EventFormData();
                    ResultSet rs = new EventTypeDatabaseDataExchange(database).getRecord(event.getEventTypeId());
                    EventType type = ResultSetToEventTypeConverter.toEventType(rs);
                    eventFormData.initWith(event, type.getEventTypeName());

                    eventForm = new FilledEventForm(eventForm, eventFormData);
                    eventForm.registerObserver(this);

                    eventForm.updateEventFormAction(eventId);

                    rightPanelContent.displayContent(eventForm);
                } else {
                    throw new IllegalArgumentException("Incorrect content in right panel!");
                }

                break;
            case REMOVE_EVENT:
                currentContent = rightPanelContent.getCurrentDisplayedContent();
                if (currentContent instanceof EventList) {
                    int eventId = ((EventList) currentContent).getEventIdFromListButton(chosenEventListButton);
                    dbExchange.deleteRecord(eventId);
                    eventList.setDate(LocalDate.now());
                    rightPanelContent.displayContent(eventList);
                } else {
                    throw new IllegalArgumentException("Incorrect content in right panel!");
                }
                break;
        }
    }

    public void displayEventTypeList() {
        rightPanelContent.displayContent(eventTypeList);
    }
}
