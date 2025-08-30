package duke.command;

import duke.task.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.LyraException;

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
