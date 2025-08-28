public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;
    
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.getSize());
    }
}
