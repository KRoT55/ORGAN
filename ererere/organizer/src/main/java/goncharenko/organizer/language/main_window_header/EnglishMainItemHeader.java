package goncharenko.organizer.language.main_window_header;


import java.io.Serializable;

public class EnglishMainItemHeader implements IMainLanguageHeader, Serializable {

    @Override
    public String[] getFileMenuHeader() {
        return new String[]{"File","Close"};
    }

    @Override
    public String[] getEditMenuHeader() {
        return new String[]{"Edit","Settings"};
    }

    @Override
    public String[] getHelpMenuHeader() {
        return new String[]{"Help","About"};
    }

    @Override
    public String[] getManageEventButtonHeader() {
        return new String[]{"New event", "Update event", "Remove event"};
    }

    @Override
    public String[] getMonthName() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
    }

    @Override
    public String[] getWeekdayHeaders() {
        return new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    }

    @Override
    public String getEventListTitleHeader() {
        return "Events";
    }

    @Override
    public String getCancelButtonHeader() {
        return "Cancel";
    }

    @Override
    public String getSubmitAddEventButtonHeader() {
        return "Add new event";
    }

    @Override
    public String getClearFormButtonHeader() {
        return "Clear form";
    }

    @Override
    public String getFormTitleHeader() {
        return "New event";
    }

    @Override
    public String getEventNameLabelHeader() {
        return "Event name";
    }

    @Override
    public String getEventDescLabelHeader() {
        return "Event description";
    }

    @Override
    public String getEventDateLabelHeader() {
        return "Event date";
    }

    @Override
    public String getEventEndDateLabelHeader() {
        return "Event end date";
    }

    @Override
    public String getEventTimeLabelHeader() {
        return "Event time";
    }

    @Override
    public String getEventEndTimeLabelHeader() {
        return "Event end time";
    }

    @Override
    public String getEventTypeLabelHeader(){return "Event type";}

    @Override
    public String getEventTypeColorHeader(){return "Event type color";}

    @Override
    public String getNewEventTypeButtonHeader(){
        return "New event type";
    }
    @Override
    public  String getRemoveEventTypeButtonHeader(){return "Remove type";}
}
