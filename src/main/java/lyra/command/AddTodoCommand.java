package duke.command;

import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Task;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.LyraException;

public class AddTodoCommand extends Command {
    private final String description;
    
    public AddTodoCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.getSize());
    }
}
