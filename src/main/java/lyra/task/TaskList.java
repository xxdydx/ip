package lyra.task;

import lyra.exception.LyraException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of tasks in the Lyra application.
 * Provides methods for adding, removing, marking, and querying tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified initial tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index from the task list.
     *
     * @param index the index of the task to delete (0-based indexing)
     * @return the deleted task
     * @throws LyraException if the index is invalid
     */
    public Task deleteTask(int index) throws LyraException {
        if (index < 0 || index >= tasks.size()) {
            throw new LyraException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index the index of the task to mark (0-based indexing)
     * @throws LyraException if the index is invalid
     */
    public void markTaskAsDone(int index) throws LyraException {
        if (index < 0 || index >= tasks.size()) {
            throw new LyraException("Invalid task number.");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks a task at the specified index as not done.
     *
     * @param index the index of the task to unmark (0-based indexing)
     * @throws LyraException if the index is invalid
     */
    public void markTaskAsNotDone(int index) throws LyraException {
        if (index < 0 || index >= tasks.size()) {
            throw new LyraException("Invalid task number.");
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Gets the list of all tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
