package duke.command;

import duke.task.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.LyraException;

public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException;
    
    public boolean isExit() {
        return false;
    }
}
