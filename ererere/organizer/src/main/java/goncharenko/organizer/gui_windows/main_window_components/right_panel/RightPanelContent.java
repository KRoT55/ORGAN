package goncharenko.organizer.gui_windows.main_window_components.right_panel;


import javafx.scene.layout.VBox;

public class RightPanelContent {

    private VBox vBox;
    private DisplayableInRightPanel content;

    public RightPanelContent(VBox vBox) {

        this.vBox = vBox;
    }

    private void clearPanelContent() {

        this.vBox.getChildren().clear();
    }

    public void displayContent(DisplayableInRightPanel content){
        clearPanelContent();
        this.content=content;
        content.display();
    }

    public DisplayableInRightPanel getCurrentDisplayedContent() {
        return content;
    }
}
