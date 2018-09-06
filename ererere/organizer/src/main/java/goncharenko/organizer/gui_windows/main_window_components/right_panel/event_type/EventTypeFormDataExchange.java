package goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type;

public class EventTypeFormDataExchange {
    private EventTypeFormFields formField;

    public EventTypeFormDataExchange(EventTypeFormFields formFields){
        this.formField=formFields;
    }

    public EventTypeFormData fetchData(){
        EventTypeFormData formData = new EventTypeFormData();
        formData.setEventTypeName(formField.getEventNameText());
        formData.setEventTypeColorName(formField.getEventTypeColor());
        return formData;
    }

    public void initFormWith(EventTypeFormData formData){
        formField.setEventNameTextField(formData.getEventTypeName());
        formField.setEventTypeColorComboBox(formData.getEventTypeColorName());
    }
}
