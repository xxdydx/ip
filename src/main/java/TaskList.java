import java.util.ArrayList;

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
        if (index < 0 || index >= tasks.size()) {
            throw new LyraException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    public void markTaskAsDone(int index) throws LyraException {
        if (index < 0 || index >= tasks.size()) {
            throw new LyraException("Invalid task number.");
        }
        tasks.get(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) throws LyraException {
        if (index < 0 || index >= tasks.size()) {
            throw new LyraException("Invalid task number.");
        }
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
}
