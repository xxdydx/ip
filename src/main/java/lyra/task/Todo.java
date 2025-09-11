package lyra.task;

/**
 * Represents a simple todo task with no deadline or time constraints.
 */
public class Todo extends Task {
    private static final String TYPE_CODE = "T";
    private static final String DONE_FLAG = "1";
    private static final String NOT_DONE_FLAG = "0";

    public Todo(String description) {
        super(description);
    }

    public static String typeCode() {
        return TYPE_CODE;
    }

    public String toDataString() {
        String status = this.isDone ? DONE_FLAG : NOT_DONE_FLAG;
        return String.join(" | ", typeCode(), status, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


