package lyra.command;

import lyra.task.TaskList;
import lyra.task.Todo;
import lyra.task.Task;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

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
