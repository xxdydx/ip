package lyra;

import lyra.util.Storage;
import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.command.Command;
import lyra.util.Parser;
import lyra.exception.LyraException;


/**
 * Main class for the Lyra task management application.
 * Command-line interface for managing todos, deadlines, and events.
 */
public class Lyra {
    private static final String DATA_FILE_PATH = "data/lyra.txt";
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lyra(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LyraException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LyraException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Lyra(DATA_FILE_PATH).run();
    }
}


