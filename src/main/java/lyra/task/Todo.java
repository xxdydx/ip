package lyra.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static String typeCode() {
        return "T";
    }

    public String toDataString() {
        return String.join(" | ", typeCode(), this.isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


