package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;


public class FilledEventForm extends EventFormDecorator{

    public FilledEventForm(EventForm eventForm,EventFormData data){
        super(eventForm);
        this.fillEventFormWith(data);
    }
}
