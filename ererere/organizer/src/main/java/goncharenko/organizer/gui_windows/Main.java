package goncharenko.organizer.gui_windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import goncharenko.organizer.OrganizerDatabase;
import goncharenko.organizer.gui_windows.main_window_components.OrganizerWindow;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Organizer");
        try {
            FXMLLoader mainOrganizerWindowLoader = new FXMLLoader(Main.class.getResource("main_window_components/OrganizerWindow.fxml"));
            AnchorPane rootPane = mainOrganizerWindowLoader.load();
            OrganizerWindow organizerWindowController = mainOrganizerWindowLoader.<OrganizerWindow>getController();

            primaryStage.setScene(new Scene(new AnchorPane(rootPane)));

//            OrganizerDatabase database = new OrganizerDatabase();
//            primaryStage.setOnCloseRequest(event->database.disconnect());

        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();
    }

}
