public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static String typeCode() {
        return "D";
    }

    public String getBy() {
        return this.by;
    }

    public String toDataString() {
        return String.join(" | ", typeCode(), this.isDone ? "1" : "0", this.description, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}


