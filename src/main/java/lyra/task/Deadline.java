package lyra.task;

import java.time.LocalDate;

import lyra.util.DateTimeUtil;

/**
 * Represents a task with a specific deadline date.
 */
public class Deadline extends Task {
    private static final String TYPE_CODE = "D";
    private static final String DONE_FLAG = "1";
    private static final String NOT_DONE_FLAG = "0";
    
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static String typeCode() {
        return TYPE_CODE;
    }

    public LocalDate getBy() {
        return this.by;
    }

    public String toDataString() {
        String status = this.isDone ? DONE_FLAG : NOT_DONE_FLAG;
        return String.join(" | ", typeCode(), status, this.description, this.by.format(DateTimeUtil.STORAGE_DATE));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.formatForDisplay(by) + ")";
    }
}


