package duke.task;

import java.time.LocalDate;

import duke.util.DateTimeUtil;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static String typeCode() {
        return "E";
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    public String toDataString() {
        String fromStr = from.format(DateTimeUtil.STORAGE_DATE);
        String toStr = to.format(DateTimeUtil.STORAGE_DATE);
        return String.join(" | ", typeCode(), this.isDone ? "1" : "0", this.description, fromStr + " to " + toStr);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeUtil.formatForDisplay(from) + " to: " + DateTimeUtil.formatForDisplay(to) + ")";
    }
}


