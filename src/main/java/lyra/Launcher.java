package lyra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lyra.util.Storage;
import lyra.task.TaskList;
import lyra.command.Command;
import lyra.util.Parser;
import lyra.exception.LyraException;

/**
 * Launcher class for the Lyra GUI application.
 * This class extends Application to properly initialize JavaFX.
 */
public class Launcher extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    private Storage storage;
    private TaskList tasks;
    private GuiUi guiUi;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        // Initialize components
        storage = new Storage("data/lyra.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (LyraException e) {
            tasks = new TaskList();
        }
        guiUi = new GuiUi();

        // Set up the main window
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Set up the layout
        stage.setTitle("Lyra");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(VBox.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(userInput, 1.0);

        // Add event handlers
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Show welcome message
        guiUi.showWelcome();
        String welcomeMessage = guiUi.getOutput();
        dialogContainer.getChildren().add(
            DialogBox.getLyraDialog(welcomeMessage, false)
        );
    }

    /**
     * Handles user input from the text field.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        // Add user input to dialog
        dialogContainer.getChildren().add(
            DialogBox.getUserDialog(input, true)
        );

        // Process command
        try {
            guiUi.clearOutput(); // Clear previous output
            Command c = Parser.parse(input);
            c.execute(tasks, guiUi, storage);
            
            // Get the output from the GUI UI
            String response = guiUi.getOutput();
            if (response.trim().isEmpty()) {
                response = "Command executed successfully.";
            }
            
            // Add response to dialog
            dialogContainer.getChildren().add(
                DialogBox.getLyraDialog(response, false)
            );

            // Check if exit command
            if (c.isExit()) {
                stage.close();
            }
        } catch (LyraException e) {
            dialogContainer.getChildren().add(
                DialogBox.getLyraDialog("Error: " + e.getMessage(), false)
            );
        }

        // Clear input and scroll to bottom
        userInput.clear();
        scrollPane.setVvalue(1.0);
    }
}
