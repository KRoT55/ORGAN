package goncharenko.organizer.gui_windows.main_window_components.right_panel.event_type.event_type_list;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.settings.Settings;
import goncharenko.organizer.settings.SettingsWithFileMemory;

import java.util.ArrayList;

public class EventTypeListControlPanel {

    private Button newEventTypeButton;
    private ArrayList<EventTypeListObserver> observers;
    private Settings settings;

    public EventTypeListControlPanel(Settings settings){
        observers =new ArrayList<>();
        newEventTypeButton = new Button();
        this.settings=settings;
        setHeaders(settings.getMainLanguageHeader());
        setNewEventTypeButtonHandler();
    }

    private void setNewEventTypeButtonHandler(){
        newEventTypeButton.setOnAction(e->notifyObservers());
    }
    private void setHeaders(IMainLanguageHeader languageMainHeader){
        newEventTypeButton.setText(languageMainHeader.getNewEventTypeButtonHeader());
    }

    public void updateButtonHeader(IMainLanguageHeader languageMainHeader){
        setHeaders(languageMainHeader);
    }

    public void addPanelToRightPanel(VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.getChildren().addAll(newEventTypeButton);
        vBox.getChildren().add(hBox);
    }

    void registerObserver(EventTypeListObserver observer){
        observers.add(observer);
    }

    void notifyObservers(){
        for(EventTypeListObserver observer:observers){
            observer.updateTypeList();
        }
    }
}
