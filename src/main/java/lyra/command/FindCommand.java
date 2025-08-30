package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.LyraException;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        ArrayList<Task> matched = new ArrayList<>();
        String needle = keyword.toLowerCase();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(needle)) {
                matched.add(task);
            }
        }
        ui.showMatchingTasks(matched);
    }
}


