package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

import javafx.scene.control.Label;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.settings.Settings;

class EventFormLabels {
    private Label eventNameLabel = new Label();
    private Label eventDescLabel = new Label();
    private Label eventDateLabel = new Label();
    private Label eventEndDateLabel = new Label();
    private Label eventTimeLabel = new Label();
    private Label eventEndTimeLabel = new Label();
    private Label eventTypeLabel = new Label();
    private Settings settings;

    EventFormLabels(Settings settings) {
        this.settings=settings;
        setHeaders();
    }

    private void setHeaders() {
        IMainLanguageHeader languageHeader = settings.getMainLanguageHeader();
        eventNameLabel.setText(languageHeader.getEventNameLabelHeader());
        eventDescLabel.setText(languageHeader.getEventDescLabelHeader());
        eventDateLabel.setText(languageHeader.getEventDateLabelHeader());
        eventEndDateLabel.setText(languageHeader.getEventEndDateLabelHeader());
        eventTimeLabel.setText(languageHeader.getEventTimeLabelHeader());
        eventEndTimeLabel.setText(languageHeader.getEventEndTimeLabelHeader());
        eventTypeLabel.setText(languageHeader.getEventTypeLabelHeader());
    }

    Label getEventNameLabel() {
        return eventNameLabel;
    }

    Label getEventDescLabelLabel() {
        return eventDescLabel;
    }

    Label getEventDateLabel() {
        return eventDateLabel;
    }

    Label getEventEndDateLabel() {
        return eventEndDateLabel;
    }

    Label getEventTimeLabel() {
        return eventTimeLabel;
    }

    Label getEventEndTimeLabel() {
        return eventEndTimeLabel;
    }

    Label getEventTypeLabel() {
        return eventTypeLabel;
    }
}
