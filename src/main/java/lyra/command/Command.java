package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException;
    
    public boolean isExit() {
        return false;
    }
}
