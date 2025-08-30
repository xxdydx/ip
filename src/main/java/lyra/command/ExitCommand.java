package duke.command;

import duke.task.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.LyraException;

public class ExitCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        ui.showGoodbye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
