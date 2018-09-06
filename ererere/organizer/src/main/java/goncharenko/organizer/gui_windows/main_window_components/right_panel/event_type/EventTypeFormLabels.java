package goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type;

import javafx.scene.control.Label;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.settings.Settings;
import goncharenko.organizer.settings.SettingsWithFileMemory;

public class EventTypeFormLabels {

    private Label eventTypeNameLabel;
    private Label eventTypeColorLabel;
    private Settings settings;

    EventTypeFormLabels(Settings settings) {
        eventTypeColorLabel = new Label();
        eventTypeNameLabel = new Label();
        this.settings=settings;
        setHeaders(settings.getMainLanguageHeader());
    }

    private void setHeaders(IMainLanguageHeader languageHeaders) {
        setEventTypeLabelHeader(languageHeaders.getEventTypeLabelHeader());
        setEventTypeColorLabel(languageHeaders.getEventTypeColorHeader());
    }

    private void setEventTypeLabelHeader(String headerString) {
        eventTypeNameLabel.setText(headerString);
    }

    private void setEventTypeColorLabel(String headerString) {
        eventTypeColorLabel.setText(headerString);
    }

    public void updateHeaders(IMainLanguageHeader languageMainHeader) {
        setHeaders(languageMainHeader);
    }

    Label[] getLabels() {
        return new Label[]{eventTypeNameLabel, eventTypeColorLabel};
    }
}
