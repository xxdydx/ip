package lyra.task;

import java.time.LocalDate;

import lyra.util.DateTimeUtil;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static String typeCode() {
        return "D";
    }

    public LocalDate getBy() {
        return this.by;
    }

    public String toDataString() {
        return String.join(" | ", typeCode(), this.isDone ? "1" : "0", this.description, this.by.format(DateTimeUtil.STORAGE_DATE));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.formatForDisplay(by) + ")";
    }
}


