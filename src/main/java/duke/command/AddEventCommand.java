package duke.command;

import duke.task.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.LyraException;
import duke.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
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
            throw new LyraException("Invalid date. Please use yyyy-MM-dd, e.g., 2019-10-15.");
        }
    }
}
