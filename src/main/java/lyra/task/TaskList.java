package lyra.task;

import lyra.exception.LyraException;
import java.util.ArrayList;

/**
 * Manages a collection of tasks in the Lyra application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws LyraException {
        validateIndex(index);
        return tasks.remove(index);
    }

    public void markTaskAsDone(int index) throws LyraException {
        validateIndex(index);
        tasks.get(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) throws LyraException {
        validateIndex(index);
        tasks.get(index).markAsNotDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    private void validateIndex(int index) throws LyraException {
        if (index < 0 || index >= tasks.size()) {
            throw new LyraException("Invalid task number.");
        }
    }
}
