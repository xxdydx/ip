package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.LyraException;

public class DeleteCommand extends Command {
    private final int taskIndex;
    
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        Task deletedTask = tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(deletedTask, tasks.getSize());
    }
}
