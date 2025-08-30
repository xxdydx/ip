package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        ui.showTaskList(tasks.getTasks());
    }
}
