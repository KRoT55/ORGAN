package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

public class EventFormDataExchange {

    private EventFormFields eventFormFields;

    public EventFormDataExchange(EventFormFields eventFormFields) {
        this.eventFormFields = eventFormFields;
    }

    public void fillEventFormWith(EventFormData data) {
        setEventName(data);
        setEventDescription(data);
        setEventBeginDate(data);
        setEventEndDate(data);
        setEventBeginTime(data);
        setEventEndTime(data);
        setEventType(data);
    }

    private void setEventName(EventFormData data) {
        eventFormFields.setEventNameField(data.getEventName());
    }

    private void setEventDescription(EventFormData data) {
        eventFormFields.setEventDescriptionField(data.getEventDescription());
    }

    private void setEventBeginDate(EventFormData data) {
        eventFormFields.setEventBeginDate(data.getEventBeginDate());
    }

    private void setEventEndDate(EventFormData data) {
        eventFormFields.setEventEndDate(data.getEventEndDate());
    }

    private void setEventBeginTime(EventFormData data) {
        eventFormFields.setEventBeginTime(data.getEventBeginTime());
    }

    private void setEventEndTime(EventFormData data) {
        eventFormFields.setEventEndTime(data.getEventEndTime());
    }

    private void setEventType(EventFormData data) {
        eventFormFields.setEventType(data.getEventTypeName());
    }

    public EventFormData fetchFormData() {
        EventFormData eventFormData = new EventFormData();
        eventFormData.setEventName(eventFormFields.getEventNameText());
        eventFormData.setEventDescription(eventFormFields.getEventDescriptionText());
        eventFormData.setEventBeginDate(eventFormFields.getEventBeginDateText());
        eventFormData.setEventEndDate(eventFormFields.getEventEndDateText());
        eventFormData.setEventBeginTime(eventFormFields.getEventBeginTimeText());
        eventFormData.setEventEndTime(eventFormFields.getEventEndTimeText());
        eventFormData.setEventTypeName(eventFormFields.getEventType());
        return eventFormData;
    }
}
