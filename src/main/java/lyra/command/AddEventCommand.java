package lyra.command;

import lyra.task.TaskList;
import lyra.task.Event;
import lyra.task.Task;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;
import lyra.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Command implementation for adding a new event task to the task list.
 */
public class AddEventCommand extends Command {
    private static final String INVALID_DATE_MESSAGE = "Invalid date. Please use yyyy-MM-dd, e.g., 2019-10-15.";
    
    private final String description;
    private final String fromRaw;
    private final String toRaw;
    
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.fromRaw = from;
        this.toRaw = to;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        try {
            LocalDate from = DateTimeUtil.parseDate(fromRaw);
            LocalDate to = DateTimeUtil.parseDate(toRaw);
            Task task = new Event(description, from, to);
            tasks.addTask(task);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(task, tasks.getSize());
        } catch (DateTimeParseException e) {
            throw new LyraException(INVALID_DATE_MESSAGE);
        }
    }
}
