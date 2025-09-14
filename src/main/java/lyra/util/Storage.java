package lyra.util;

import lyra.task.Task;
import lyra.task.Todo;
import lyra.task.Deadline;
import lyra.task.Event;
import lyra.task.TaskList;
import lyra.exception.LyraException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Handles persistent storage of tasks in the Lyra application.
 * Supports automatic creation of data directory and file if they don't exist.
 */
public class Storage {
    private static final String TASK_DONE_FLAG = "1";
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String EVENT_SEPARATOR = " to ";
    private static final int MIN_PARTS_LENGTH = 3;
    private static final int DEADLINE_PARTS_LENGTH = 4;
    
    private final Path dataFile;

    public Storage(String filePath) {
        this.dataFile = Paths.get(filePath);
    }

    /**
     * Loads tasks from the data file.
     * Creates the data directory and file if they don't exist.
     */
    public ArrayList<Task> load() throws LyraException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path dataDir = dataFile.getParent();
        
        try {
            if (Files.exists(dataFile)) {
                for (String fileLine : Files.readAllLines(dataFile)) {
                    Task task = parseTaskFromFile(fileLine);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } else {
                if (!Files.exists(dataDir)) {
                    Files.createDirectories(dataDir);
                }
                Files.createFile(dataFile);
            }
        } catch (IOException e) {
            throw new LyraException("Error loading tasks from file: " + e.getMessage());
        }
        
        return tasks;
    }

    /**
     * Saves the current list of tasks to the data file.
     */
    public void save(ArrayList<Task> tasks) throws LyraException {
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Todo) {
                lines.add(((Todo) task).toDataString());
            } else if (task instanceof Deadline) {
                lines.add(((Deadline) task).toDataString());
            } else if (task instanceof Event) {
                lines.add(((Event) task).toDataString());
            }
        }
        
        try {
            Files.write(dataFile, lines);
        } catch (IOException e) {
            throw new LyraException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the data file and reconstructs the corresponding Task object.
     * Supports formats: T|status|description, D|status|description|deadline, E|status|description|from to to
     */
    private Task parseTaskFromFile(String fileLine) {
        String[] parts = fileLine.split("\\|", -1);
        
        if (parts.length < MIN_PARTS_LENGTH) {
            return null;
        }
        
        String type = parts[0].trim();
        String doneFlag = parts[1].trim();
        String description = parts[2].trim();
        Task task = null;
        
        if (TASK_TYPE_TODO.equalsIgnoreCase(type)) {
            task = new Todo(description);
        } else if (TASK_TYPE_DEADLINE.equalsIgnoreCase(type) && parts.length >= DEADLINE_PARTS_LENGTH) {
            String byStr = parts[3].trim();
            LocalDate by = LocalDate.parse(byStr, DateTimeUtil.STORAGE_DATE);
            task = new Deadline(description, by);
        } else if (TASK_TYPE_EVENT.equalsIgnoreCase(type) && parts.length >= DEADLINE_PARTS_LENGTH) {
            String range = parts[3].trim();
            String from;
            String to;
            int sepIdx = range.lastIndexOf(EVENT_SEPARATOR);
            if (sepIdx >= 0) {
                from = range.substring(0, sepIdx);
                to = range.substring(sepIdx + EVENT_SEPARATOR.length());
            } else {
                from = range;
                to = "";
            }
            LocalDate fromDate = LocalDate.parse(from.trim(), DateTimeUtil.STORAGE_DATE);
            LocalDate toDate = LocalDate.parse(to.trim(), DateTimeUtil.STORAGE_DATE);
            task = new Event(description, fromDate, toDate);
        }
        
        if (task != null && TASK_DONE_FLAG.equals(doneFlag)) {
            task.markAsDone();
        }
        
        return task;
    }
}
