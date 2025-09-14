package lyra;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Font;

/**
 * DialogBox class for displaying user and bot messages in the GUI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setFont(Font.font("Segoe UI", 13));
        text.setMinHeight(Label.USE_PREF_SIZE);
        text.setMaxWidth(300);
        
        displayPicture.setFitWidth(40.0);
        displayPicture.setFitHeight(40.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(12);
        this.setPadding(new javafx.geometry.Insets(8));
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, boolean isUser) {
        var db = new DialogBox(new Label(text), new ImageView());
        db.text.setStyle("-fx-background-color: #0078d4; -fx-background-radius: 15; -fx-padding: 12; -fx-text-fill: white; -fx-font-weight: bold;");
        db.flip();
        return db;
    }

    public static DialogBox getLyraDialog(String text, boolean isUser) {
        var db = new DialogBox(new Label(text), new ImageView());
        db.text.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 15; -fx-padding: 12; -fx-text-fill: #333333; -fx-border-color: #e0e0e0; -fx-border-width: 1; -fx-border-radius: 15;");
        return db;
    }
}
