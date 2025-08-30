package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

public class UnmarkCommand extends Command {
    private final int taskIndex;
    
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        tasks.markTaskAsNotDone(taskIndex);
        storage.save(tasks.getTasks());
        ui.showTaskMarked(tasks.getTasks().get(taskIndex), false);
    }
}
