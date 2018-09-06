package goncharenko.organizer.gui_windows.main_window_components.calendar_element;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class CalendarWeekdayLabelArray {
    private Label[] labelArray;

    public CalendarWeekdayLabelArray(Label firstWeekdayLabel, Label secondWeekdayLabel, Label thirdWeekdayLabel, Label fourthWeekdayLabel, Label fifthWeekdayLabel, Label sixthWeekdayLabel, Label seventhWeekdayLabel) {
        labelArray = new Label[7];
        joinLabelsIntoArray(firstWeekdayLabel, secondWeekdayLabel, thirdWeekdayLabel, fourthWeekdayLabel, fifthWeekdayLabel, sixthWeekdayLabel, seventhWeekdayLabel);;
    }


    private void joinLabelsIntoArray(Label firstWeekdayLabel, Label secondWeekdayLabel, Label thirdWeekdayLabel, Label fourthWeekdayLabel, Label fifthWeekdayLabel, Label sixthWeekdayLabel, Label seventhWeekdayLabel) {
        labelArray[0] = firstWeekdayLabel;
        labelArray[1] = secondWeekdayLabel;
        labelArray[2] = thirdWeekdayLabel;
        labelArray[3] = fourthWeekdayLabel;
        labelArray[4] = fifthWeekdayLabel;
        labelArray[5] = sixthWeekdayLabel;
        labelArray[6] = seventhWeekdayLabel;
    }

    public void fillWeekdayLabels(String[] labelHeaders){
        for(int i=0;i<labelArray.length;++i){
            labelArray[i].setText(labelHeaders[i]);
            Font font = Font.font("System", FontWeight.BOLD,13);
            labelArray[i].setFont(font);

        }
        labelArray[6].setTextFill(Color.RED);
    }
}
