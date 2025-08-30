package lyra.command;

import lyra.task.TaskList;
import lyra.task.Deadline;
import lyra.task.Task;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;
import lyra.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String byRaw;
    
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.byRaw = by;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        try {
            LocalDate by = DateTimeUtil.parseDate(byRaw);
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(task, tasks.getSize());
        } catch (DateTimeParseException e) {
            throw new LyraException("Invalid date. Please use yyyy-MM-dd, e.g., 2019-10-15.");
        }
    }
}
