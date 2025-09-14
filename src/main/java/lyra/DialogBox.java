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
        text.setFont(Font.font("Arial", 12));
        text.setMinHeight(Label.USE_PREF_SIZE);
        
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.setPadding(new javafx.geometry.Insets(10));
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
        db.text.setStyle("-fx-background-color: #e3f2fd; -fx-background-radius: 10; -fx-padding: 10;");
        db.flip();
        return db;
    }

    public static DialogBox getLyraDialog(String text, boolean isUser) {
        var db = new DialogBox(new Label(text), new ImageView());
        db.text.setStyle("-fx-background-color: #f3e5f5; -fx-background-radius: 10; -fx-padding: 10;");
        return db;
    }
}
