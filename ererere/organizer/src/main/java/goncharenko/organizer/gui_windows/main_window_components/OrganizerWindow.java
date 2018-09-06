package goncharenko.organizer.gui_windows.main_window_components;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.gui_windows.SettingsWindow;
import goncharenko.organizer.gui_windows.main_window_components.calendar_element.CalendarGrid;
import goncharenko.organizer.gui_windows.main_window_components.right_panel.RightPanel;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.settings.Settings;
import goncharenko.organizer.settings.SettingsLoader;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class OrganizerWindow implements Initializable {

    @FXML
    private Button newEventButton;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private Button removeEventButton;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private Menu editMenu;
    @FXML
    private MenuItem settingMenuItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private Label firstWeekdayLabel;
    @FXML
    private Label secondWeekdayLabel;
    @FXML
    private Label thirdWeekdayLabel;
    @FXML
    private Label fourthWeekdayLabel;
    @FXML
    private Label fifthWeekdayLabel;
    @FXML
    private Label sixthWeekdayLabel;
    @FXML
    private Label seventhWeekdayLabel;
    @FXML
    private GridPane calendarGridPane;
    @FXML
    private Button updateEventButton;
    @FXML
    private Label aboveEventInfoTitleLabel;
    @FXML
    private Label calendarMonthLabel;
    @FXML
    private Label calendarYearLabel;
    @FXML
    private ScrollPane eventScrollPane;
    @FXML
    private AnchorPane rightPanelRootPane;
    @FXML
    private MenuItem manageEventType;

    private IMainLanguageHeader itemLanguageHeaders;
    private CalendarGrid calendarGrid;
    private int currentChosenMonthNumber, currentChosenYearNumber;
    private RightPanel rightPanel;
    private EventManagmentPanel eventManagmentPanel;
    private OrganizerDatabase database;
    private Settings settings;

    public void initialize(URL url, ResourceBundle rb) {
        updateEventButton.setDisable(true);
        removeEventButton.setDisable(true);
        database = new OrganizerDatabase();
        database.establishConnection();

        settings = SettingsLoader.loadSettings();
        itemLanguageHeaders = settings.getMainLanguageHeader();
        setMenuItemHeaders();

        calendarGrid = new CalendarGrid(calendarGridPane, database);

        eventManagmentPanel = new EventManagmentPanel(newEventButton, updateEventButton, removeEventButton);
        rightPanel = new RightPanel(aboveEventInfoTitleLabel, rightPanelRootPane, eventManagmentPanel, database,settings);

        calendarGrid.passWeekdayLabels(firstWeekdayLabel, secondWeekdayLabel, thirdWeekdayLabel, fourthWeekdayLabel, fifthWeekdayLabel, sixthWeekdayLabel, seventhWeekdayLabel);

        currentChosenMonthNumber = LocalDate.now().getMonthValue();
        currentChosenYearNumber = LocalDate.now().getYear();
        calendarGrid.setCurrentChosenMonth(currentChosenMonthNumber);
        calendarGrid.setCurrentChosenYear(currentChosenYearNumber);
        calendarGrid.fillCalendarWith(currentChosenMonthNumber, currentChosenYearNumber);
        calendarGrid.fillWeekdayLabels(itemLanguageHeaders.getWeekdayHeaders());
        setMonthLabelText();
        setYearLabelWithCurrentYear();
        setEventTitleHeader();
    }

    private void setEventTitleHeader() {

        aboveEventInfoTitleLabel.setText(itemLanguageHeaders.getEventListTitleHeader());
    }

    private void setYearLabelWithCurrentYear() {
        LocalDate now = LocalDate.now();
        calendarYearLabel.setText(String.valueOf(now.getYear()));
    }

    public void previousMonthButtonHandler(ActionEvent ae) {
        if (--currentChosenMonthNumber < 0) currentChosenMonthNumber = 12;
        updateCalendarElements();
        setMonthLabelText();
        calendarGrid.setCurrentChosenMonth(currentChosenMonthNumber);
    }

    public void nextMonthButtonHandler(ActionEvent ae) {
        if (++currentChosenMonthNumber > 12) currentChosenMonthNumber = 1;
        updateCalendarElements();
        setMonthLabelText();
        calendarGrid.setCurrentChosenMonth(currentChosenMonthNumber);
    }

    public void previousYearButtonHandler(ActionEvent ae) {
        --currentChosenYearNumber;
        updateYearLabelText();
        updateCalendarElements();
        calendarGrid.setCurrentChosenYear(currentChosenYearNumber);
    }

    public void nextYearButtonHandler(ActionEvent ae) {
        ++currentChosenYearNumber;
        updateYearLabelText();
        updateCalendarElements();
        calendarGrid.setCurrentChosenYear(currentChosenYearNumber);
    }

    private void setMonthLabelText() {
        calendarMonthLabel.setText(itemLanguageHeaders.getMonthName()[currentChosenMonthNumber - 1]);
    }

    private void updateYearLabelText() {
        calendarYearLabel.setText(String.valueOf(currentChosenYearNumber));
    }

    private void updateCalendarElements() {
        calendarGrid.fillCalendarWith(currentChosenMonthNumber, currentChosenYearNumber);
    }

    public void settingMenuItemHandler(ActionEvent ae) {
        Platform.runLater(() -> new SettingsWindow(this).start(new Stage()));
    }

    public void closeMenuItemHandler(ActionEvent ae) {
        Platform.exit();
    }

    public void loadNewSettingsFromFile() {

        settings = SettingsLoader.loadSettings();
    }

    private void setMenuItemHeaders() {
        fileMenu.setText(itemLanguageHeaders.getFileMenuHeader()[0]);
        closeMenuItem.setText(itemLanguageHeaders.getFileMenuHeader()[1]);
        editMenu.setText(itemLanguageHeaders.getEditMenuHeader()[0]);
        settingMenuItem.setText(itemLanguageHeaders.getEditMenuHeader()[1]);
        helpMenu.setText(itemLanguageHeaders.getHelpMenuHeader()[0]);
        aboutMenuItem.setText(itemLanguageHeaders.getHelpMenuHeader()[1]);

        newEventButton.setText(itemLanguageHeaders.getManageEventButtonHeader()[0]);
        updateEventButton.setText(itemLanguageHeaders.getManageEventButtonHeader()[1]);
        removeEventButton.setText(itemLanguageHeaders.getManageEventButtonHeader()[2]);
    }

    public void manageEventTypeMenuHandler() {
        rightPanel.displayEventTypeList();
    }
}
