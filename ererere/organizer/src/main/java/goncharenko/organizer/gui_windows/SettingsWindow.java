package goncharenko.organizer.gui_windows;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import goncharenko.organizer.gui_windows.main_window_components.OrganizerWindow;
import goncharenko.organizer.language.setting_window_header.EnglishSettingItemHeaders;
import goncharenko.organizer.language.setting_window_header.RussianSettingItemHeaders;
import goncharenko.organizer.settings.SettingsWithFileMemory;
import goncharenko.organizer.language.AppLanguage;
import goncharenko.organizer.language.setting_window_header.ILanguageSettingHeader;
import goncharenko.organizer.settings.Settings;

public class SettingsWindow extends Application {

    //settings components
    private SettingsWithFileMemory settingsWithFileMemory;
    private Settings settings;
    private ILanguageSettingHeader languageHeader;

    //window's components
    private GridPane settingsPane;
    private BorderPane rootNode;
    private GridPane mainSettingsNode;
    private Stage windowStage;
    private Scene windowScene;
    private HBox controlButtonsHBox;

    //parent window
    private OrganizerWindow organizerWindow;

    public SettingsWindow(OrganizerWindow organizerWindow) {
        this.organizerWindow=organizerWindow;
    }

    @Override
    public void init() {
        //app settings are loaded at main window start
        //then AppSettings object is passed on settings' window start
        this.settingsWithFileMemory = new SettingsWithFileMemory();
        settingsWithFileMemory.loadFromFile();
        settings = settingsWithFileMemory.getSettingsObject();
    }

    @Override
    public void start(Stage settingStage) {
        //manual call init() method due to manual call start() method in OrganizerWindow
        init();
        windowStage = settingStage;
        languageHeader = settings.getSettingsLanguageHeader();
        windowStage.setTitle(languageHeader.getSettingTitleHeader());

        initRootPane();
        createScene();
        setScene();

        setSettingsGridMinimalHeight();
        addSettingsTitle();

        addSettingsGridToMainSettingsNode();

        addSettingsComponentToSettingsGrid();

        createControlButtonsHBox();
        addButtonsToControlHBox();
        addControlHBoxToMainSettingsNode();

        addMainSettingsNodeToRootNode();

        setRootNodeMinimalBounds();

        settingStage.show();
    }

    private void initRootPane() {
        rootNode = new BorderPane();
        mainSettingsNode = new GridPane();

        settingsPane = new GridPane();
        settingsPane.setPadding(new Insets(30));
        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.setVgap(25);
        settingsPane.setHgap(15);
    }

    private void createScene() {
        windowScene = new Scene(rootNode, 800, 450);
    }

    private void setScene() {
        windowStage.setScene(windowScene);
    }

    private void setSettingsGridMinimalHeight() {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(50);
        mainSettingsNode.getRowConstraints().add(0, rowConstraints);
    }

    private void addSettingsGridToMainSettingsNode() {
        mainSettingsNode.add(settingsPane, 0, 1);
        mainSettingsNode.setAlignment(Pos.CENTER);
    }

    private void addSettingsComponentToSettingsGrid() {
        addLanguageSetting();
        addFontSizeSetting();
        addAlertTimeSetting();
        addStartOnBootSetting();
    }

    private void createControlButtonsHBox() {
        controlButtonsHBox = new HBox();
        controlButtonsHBox.setAlignment(Pos.CENTER);
        controlButtonsHBox.setPadding(new Insets(15));
    }


    private void addControlHBoxToMainSettingsNode() {
        mainSettingsNode.add(controlButtonsHBox, 0, 2);
        GridPane.setHalignment(controlButtonsHBox, HPos.CENTER);
    }

    private void addMainSettingsNodeToRootNode() {
        rootNode.setCenter(mainSettingsNode);
    }

    private void setRootNodeMinimalBounds() {
        windowStage.setMinWidth(600);
        windowStage.setMinHeight(400);
    }

    //=========
    //setting's components' methods
    private void addSettingsTitle() {
        Label settingLabel = new Label(languageHeader.getSettingTitleHeader());
        settingLabel.setFont(new Font(settings.getFontSizeSetting() * 2));
        GridPane.setHalignment(settingLabel, HPos.CENTER);
        GridPane.setValignment(settingLabel, VPos.TOP);
        mainSettingsNode.add(settingLabel, 0, 0);
    }

    private void addLanguageSetting() {
        Label changeLanguageInfoLabel = new Label();
        changeLanguageInfoLabel.visibleProperty().setValue(false);
        changeLanguageInfoLabel.setFont(Font.font(11));
        ObservableList<String> languageList = FXCollections.observableArrayList(settings.getAvailableLanguage());
        ComboBox<String> languageComboBox = new ComboBox<>(languageList);
        if (settings.getLanguageSetting() == AppLanguage.ENGLISH)
            languageComboBox.setValue("English");
        else languageComboBox.setValue("Russian");
        Label languageLabel = new Label(languageHeader.getLanguageSettingHeader());
        languageLabel.setContentDisplay(ContentDisplay.RIGHT);

        languageComboBox.setOnAction(event -> {
            String choosenLanguage = languageComboBox.getValue();
            switch (choosenLanguage) {
                case "English":
                    settings.setLanguage(AppLanguage.ENGLISH);
                    changeLanguageInfoLabel.setText(new EnglishSettingItemHeaders().getChangeLanguageInfoLabelHeader());
                    break;
                case "Russian":
                    settings.setLanguage(AppLanguage.RUSSIAN);
                    changeLanguageInfoLabel.setText(new RussianSettingItemHeaders().getChangeLanguageInfoLabelHeader());
                    break;
            }
            changeLanguageInfoLabel.visibleProperty().setValue(true);

        });
        settingsPane.add(languageLabel, 0, 0);
        settingsPane.add(languageComboBox, 1, 0);
        settingsPane.add(changeLanguageInfoLabel,0,1,2,1);
    }

    private void addFontSizeSetting() {
        TextField fontSizeField = new TextField(Integer.toString(settings.getFontSizeSetting()));
        fontSizeField.setMaxWidth(50);
        Label fontSizeLabel = new Label(languageHeader.getFontSizeSettingHeader());
        fontSizeLabel.setContentDisplay(ContentDisplay.RIGHT);

        fontSizeField.setOnMouseExited((event) -> settings.setFontSizeSetting(Integer.parseInt(fontSizeField.getText())));

        settingsPane.add(fontSizeLabel, 0, 2);
        settingsPane.add(fontSizeField, 1, 2);
    }

    private void addAlertTimeSetting() {
        addAlertTimeCheckBoxSetting();
        addAlertTimeValueSetting();
    }

    private void addAlertTimeCheckBoxSetting() {
        Label alertTimeCheckboxLabel = new Label(languageHeader.getAlertSettingHeader());
        CheckBox alertTimeCheckBox = new CheckBox();

        alertTimeCheckBox.setSelected(settings.getAlertSetting());

        alertTimeCheckBox.setOnAction((event) -> {
            settings.setAlertSetting(alertTimeCheckBox.isSelected());
            settingsPane.getChildren().get(6).setDisable(!settings.getAlertSetting());
            settingsPane.getChildren().get(7).setDisable(!settings.getAlertSetting());
        });

        settingsPane.add(alertTimeCheckboxLabel, 0, 3);
        settingsPane.add(alertTimeCheckBox, 1, 3);

    }

    private void addAlertTimeValueSetting() {
        ObservableList<String> timeUnitList = FXCollections.observableArrayList(languageHeader.getAlertTimeUnitSettingHeaderArray());
        ComboBox<String> alertTimeUnitComboBox = new ComboBox<>(timeUnitList);
        alertTimeUnitComboBox.setValue(settings.getAlertTimeUnitSetting());
        Label alertTimeLabel = new Label(languageHeader.getAlertTimeSettingHeader());
        TextField alertTimeField = new TextField(Integer.toString(settings.getAlertTimeSetting()));
        alertTimeField.setMaxWidth(50);

        HBox alertTimeUnitBox = new HBox();
        alertTimeUnitBox.getChildren().add(alertTimeField);
        alertTimeUnitBox.getChildren().add(alertTimeUnitComboBox);

        alertTimeUnitComboBox.setValue(settings.getAlertTimeUnitSetting());
        alertTimeUnitBox.setDisable(!settings.getAlertSetting());
        alertTimeLabel.setDisable(!settings.getAlertSetting());

        alertTimeField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    settings.setAlertTimeSetting(Integer.parseInt(alertTimeField.getText()));
            }
        });
        alertTimeUnitComboBox.setOnAction((event) -> settings.setAlertTimeUnitSetting(alertTimeUnitComboBox.getValue()));

        settingsPane.add(alertTimeLabel, 0, 4);
        settingsPane.add(alertTimeUnitBox, 1, 4);
    }

    private void addStartOnBootSetting() {
        Label label = new Label(languageHeader.getStartOnBootSettingHeader());
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(settings.getStartOnBootSetting());

        checkBox.setOnAction((event) -> settings.setStartOnSystemBootSetting(checkBox.isSelected()));

        settingsPane.add(label, 0, 5);
        settingsPane.add(checkBox, 1, 5);
    }

    private void addButtonsToControlHBox() {
        addResetButtonToControlHBox();
        addOKButtonToControlHBox();
        addCancelButtonToControlHBox();
        addApplyButtonToControlHBox();
    }

    private void addOKButtonToControlHBox() {
        Button OKButton = new Button("OK");
        OKButton.setOnAction((event) -> {
            settingsWithFileMemory.saveToFile();
            windowStage.close();
            organizerWindow.loadNewSettingsFromFile();
        });
        controlButtonsHBox.getChildren().add(OKButton);
    }

    private void addApplyButtonToControlHBox() {
        Button applyButton = new Button(languageHeader.getApplyButtonHeader());
        applyButton.setOnAction(event -> settingsWithFileMemory.saveToFile());
        controlButtonsHBox.getChildren().addAll(applyButton);
    }

    private void addCancelButtonToControlHBox() {
        Button cancelButton = new Button(languageHeader.getCancelButtonHeader());
        cancelButton.setOnAction(event -> windowStage.close());
        controlButtonsHBox.getChildren().add(cancelButton);
    }

    private void addResetButtonToControlHBox() {
        Button resetButton = new Button(languageHeader.getLoadDefaultButtonHeader());
        resetButton.setOnAction(event -> {
            settings.loadDefaultSettings();
            windowStage.close();
            start(new Stage());
        });
        controlButtonsHBox.getChildren().add(resetButton);
    }
}
