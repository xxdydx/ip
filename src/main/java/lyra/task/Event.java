package lyra.task;

import java.time.LocalDate;

import lyra.util.DateTimeUtil;

/**
 * Represents a task that occurs during a specific time period.
 */
public class Event extends Task {
    private static final String TYPE_CODE = "E";
    private static final String DONE_FLAG = "1";
    private static final String NOT_DONE_FLAG = "0";
    private static final String EVENT_SEPARATOR = " to ";
    
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static String typeCode() {
        return TYPE_CODE;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    public String toDataString() {
        String status = this.isDone ? DONE_FLAG : NOT_DONE_FLAG;
        String fromStr = from.format(DateTimeUtil.STORAGE_DATE);
        String toStr = to.format(DateTimeUtil.STORAGE_DATE);
        return String.join(" | ", typeCode(), status, this.description, fromStr + EVENT_SEPARATOR + toStr);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeUtil.formatForDisplay(from) + " to: " + DateTimeUtil.formatForDisplay(to) + ")";
    }
}


