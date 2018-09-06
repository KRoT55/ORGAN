package goncharenko.organizer.gui_windows.main_window_components.right_panel.event.event_form;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

 class EventFormFields {
    private TextField eventNameField = new TextField();
    private TextArea eventDescArea = new TextArea();
    private TextField eventDateField = new TextField();
    private TextField eventEndDateField = new TextField();
    private TextField eventTimeField = new TextField();
    private TextField eventEndTimeField = new TextField();
    private ComboBox<String> eventTypeComboBox = new ComboBox<>();


    void setEventNameField(String name) {
        eventNameField.setText(name);
    }

    void setEventDescriptionField(String description) {
        eventDescArea.setText(description);
    }

    void setEventBeginDate(String date) {

        eventDateField.setText(date);
    }

    void setEventEndDate(String date) {

        eventEndDateField.setText(date);
    }

    void setEventBeginTime(String time) {

        eventTimeField.setText(time);
    }

    void setEventEndTime(String time) {

        eventEndTimeField.setText(time);
    }

    void setEventType(String type) {
        eventTypeComboBox.setValue(type);
    }


    String getEventNameText() {
        return eventNameField.getText();
    }

    String getEventDescriptionText() {
        return eventDescArea.getText();
    }

    String getEventBeginDateText() {

        return eventDateField.getText();
    }

    String getEventEndDateText() {

        return eventEndDateField.getText();
    }

    String getEventBeginTimeText() {

        return eventTimeField.getText();
    }

    String getEventEndTimeText() {

        return eventEndTimeField.getText();
    }

    void clearAllField() {
        eventNameField.setText("");
        eventDescArea.setText("");
        eventDateField.setText("");
        eventEndDateField.setText("");
        eventTimeField.setText("");
        eventEndTimeField.setText("");
        eventTypeComboBox.setValue(eventTypeComboBox.getItems().get(0));
    }

    TextField getEventNameField() {
        return eventNameField;
    }

    TextArea getEventDescArea() {
        return eventDescArea;
    }

    TextField getEventDateField() {
        return eventDateField;
    }

    TextField getEventEndDateField() {
        return eventEndDateField;
    }

    TextField getEventTimeField() {
        return eventTimeField;
    }

    TextField getEventEndTimeField() {
        return eventEndTimeField;
    }

    String getEventType() {
        return eventTypeComboBox.getValue();
    }

    ComboBox<String> getEventTypeComboBox() {
        return eventTypeComboBox;
    }
}
