package goncharenko.organizer.gui_windows.main_window_components.calendar_element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.collections.EventCollection;
import goncharenko.organizer.gui_windows.main_window_components.CalendarDayHandler;
import goncharenko.organizer.organizer_database.EventCollectionWithDownloader;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Collectors;


public class CalendarGrid {

    private GridPane calendarGridPane;
    private Button[] gridElements;
    private CalendarWeekdayLabelArray calendarWeekdayLabelArray;
    private int currentChosenMonth;
    private int currentChosenYear;
    private CalendarDayHandler dayHandler;
    private OrganizerDatabase database;
    private int currentYear;
    private int currentMonth;

    public CalendarGrid(GridPane calendarGridPane, OrganizerDatabase database) {
        try {
            this.calendarGridPane = calendarGridPane;
        } catch (NullPointerException e) {
            System.out.println("CalendarGrid.getInstance(): passed null parametr!");
            e.printStackTrace();
        }
        this.database = database;
        gridElements = new Button[42];
        convertGridButtonToArray();
        addHandlerToCalendarButtons();
        dayHandler = CalendarDayHandler.getInstance();
    }


    private void convertGridButtonToArray() {
        sortGridElementsByGridIndexes();
        int elementIndexInterator = 0;
        for (int element = 0; element < calendarGridPane.getChildren().size(); ++element) {
            Node node = calendarGridPane.getChildren().get(element);
            if (node instanceof Button) {
                Button button = (Button) node;
                gridElements[elementIndexInterator++] = button;
            }
        }
    }


    private void sortGridElementsByGridIndexes() {
        sortGridElementByRowIndexes();
        sortGridRowsByColumnIndexes();
    }

    private void sortGridElementByRowIndexes() {
        FXCollections.sort(calendarGridPane.getChildren(), (node1, node2) -> {
                    int[] row = new int[2];
                    row[0] = GridPane.getRowIndex(node1);
                    row[1] = GridPane.getRowIndex(node2);
                    if (row[0] < row[1]) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
        );
    }

    private void sortGridRowsByColumnIndexes() {
        ObservableList<Node> sortedColumnList = null;
        for (int i = 0; i < 7; i++) {
            ObservableList<Node> sortedRowList = calendarGridPane.getChildren()
                    .stream()
                    .skip(7 * i)
                    .limit(7)
                    .sorted((n1, n2) -> {
                        int[] column = new int[2];
                        column[0] = GridPane.getColumnIndex(n1);
                        column[1] = GridPane.getColumnIndex(n2);
                        if (column[0] < column[1])
                            return -1;
                        else
                            return 1;
                    }).collect(Collectors.toCollection(FXCollections::observableArrayList));
            if (sortedColumnList == null) sortedColumnList = FXCollections.observableList(sortedRowList);
            else sortedColumnList.addAll(sortedRowList);
        }
        calendarGridPane.getChildren().clear();
        calendarGridPane.getChildren().addAll(sortedColumnList);
    }

    public void fillCalendarWith(int month, int year) {
        this.currentMonth = month;
        this.currentYear = year;

        clearCalendar();
        LocalDate date = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        int offsetByDayOfWeek = date.getDayOfWeek().getValue() - 1;
        date = date.minus(offsetByDayOfWeek, ChronoUnit.DAYS);
        disableOffsetButton(offsetByDayOfWeek);

        for (int day = 0; day < gridElements.length; ++day, date = date.plus(1, ChronoUnit.DAYS)) {
            if (date.compareTo(lastDayOfMonth) > 0) {
                gridElements[day].setDisable(true);
            }
            gridElements[day].setText(String.valueOf(date.getDayOfMonth()));
            if(date.getDayOfWeek()==DayOfWeek.SUNDAY)
                gridElements[day].setTextFill(Color.RED);
            Font font = Font.font("System", FontWeight.BOLD,13);

            gridElements[day].setFont(font);
        }
        drawCircleMarkOnButton();
    }

    private void disableOffsetButton(int offset) {
        for (int i = 0; i < offset; i++)
            gridElements[i].setDisable(true);
    }

    private void clearCalendar() {
        for (Button day : gridElements) {
            day.setText("");
            day.setDisable(false);
        }
    }

    public void passWeekdayLabels(Label firstWeekdayLabel, Label secondWeekdayLabel, Label thirdWeekdayLabel, Label fourthWeekdayLabel, Label fifthWeekdayLabel, Label sixthWeekdayLabel, Label seventhWeekdayLabel) {
        calendarWeekdayLabelArray = new CalendarWeekdayLabelArray(firstWeekdayLabel, secondWeekdayLabel, thirdWeekdayLabel, fourthWeekdayLabel, fifthWeekdayLabel, sixthWeekdayLabel, seventhWeekdayLabel);
    }

    public void fillWeekdayLabels(String[] labelHeaders) {
        calendarWeekdayLabelArray.fillWeekdayLabels(labelHeaders);
    }

    private void addHandlerToCalendarButtons() {
        for (Button button : gridElements) {
            button.setOnAction((e) -> {
                clearCalendarButtonBackground();
                ((Button)e.getSource()).setStyle("-fx-border-width: 3px;-fx-border-color: #0099e5");
                dayHandler.handleDay(LocalDate.of(currentChosenYear, currentChosenMonth, Integer.parseInt(button.getText())));
            });
        }
    }

    private void clearCalendarButtonBackground(){
        for(Button button:gridElements){
            button.setStyle("-fx-border-width:0px");
        }
    }
    public void setCurrentChosenMonth(int value) {
        this.currentChosenMonth = value;
    }

    public void setCurrentChosenYear(int value) {
        this.currentChosenYear = value;
    }

    private void drawCircleMarkOnButton() {
        EventCollection collection = getMonthEvents();
//        for(int i=0;i<gridElements.length;i++){
            String text = gridElements[0].getText();
            Circle circle = new Circle();
            circle.setRadius(100);
            circle.setCenterX(100);
            circle.setCenterY(100);
            circle.setFill(Color.BLUE);
            gridElements[0]= new Button(text,circle);
//        }
    }

    private EventCollection getMonthEvents() {
        EventCollectionWithDownloader downloader = new EventCollectionWithDownloader(database);
        LocalDate date = LocalDate.of(currentYear,currentMonth,1);
        return downloader.getEventCollectionBetween(date,date.with(TemporalAdjusters.lastDayOfMonth()));
    }


}
