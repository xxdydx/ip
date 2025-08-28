package duke.util;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.exception.LyraException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {
    private final Path dataFile;

    public Storage(String filePath) {
        this.dataFile = Paths.get(filePath);
    }

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
                // Create directory and file if they don't exist
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

    private Task parseTaskFromFile(String fileLine) {
        String[] parts = fileLine.split("\\|", -1);
        // Expect formats:
        // T | 1 | description
        // D | 0 | description | by
        // E | 0 | description | from to to
        if (parts.length >= 3) {
            String type = parts[0].trim();
            String doneFlag = parts[1].trim();
            String description = parts[2].trim();
            Task task = null;
            
            if ("T".equalsIgnoreCase(type)) {
                task = new Todo(description);
            } else if ("D".equalsIgnoreCase(type) && parts.length >= 4) {
                String byStr = parts[3].trim();
                LocalDate by = LocalDate.parse(byStr, DateTimeUtil.STORAGE_DATE);
                task = new Deadline(description, by);
            } else if ("E".equalsIgnoreCase(type) && parts.length >= 4) {
                // For simplicity, we store the range as a single field "from to to"
                String range = parts[3].trim();
                String from;
                String to;
                int sepIdx = range.lastIndexOf(" to ");
                if (sepIdx >= 0) {
                    from = range.substring(0, sepIdx);
                    to = range.substring(sepIdx + 4);
                } else {
                    from = range;
                    to = "";
                }
                LocalDate fromDate = LocalDate.parse(from.trim(), DateTimeUtil.STORAGE_DATE);
                LocalDate toDate = LocalDate.parse(to.trim(), DateTimeUtil.STORAGE_DATE);
                task = new Event(description, fromDate, toDate);
            }
            
            if (task != null && "1".equals(doneFlag)) {
                task.markAsDone();
            }
            
            return task;
        }
        return null;
    }
}
