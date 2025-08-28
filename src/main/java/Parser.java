public class Parser {
    
    public static Command parse(String fullCommand) throws LyraException {
        String trimmedCommand = fullCommand.trim();
        
        if (trimmedCommand.isEmpty()) {
            throw new LyraException("Please enter a command. Type 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete', or 'bye'.");
        }
        
        String[] parts = trimmedCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        
        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return parseTodoCommand(arguments);
            case "deadline":
                return parseDeadlineCommand(arguments);
            case "event":
                return parseEventCommand(arguments);
            case "mark":
                return parseMarkCommand(arguments);
            case "unmark":
                return parseUnmarkCommand(arguments);
            case "delete":
                return parseDeleteCommand(arguments);
            default:
                throw new LyraException("Sorry, I couldn't recognize that command. Try one of: list, todo, deadline, event, mark, unmark, delete, bye.");
        }
    }
    
    private static Command parseTodoCommand(String arguments) throws LyraException {
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Sorry, a todo needs a description. Try: todo <description>");
        }
        return new AddTodoCommand(arguments.trim());
    }
    
    private static Command parseDeadlineCommand(String arguments) throws LyraException {
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>");
        }
        
        String[] split = arguments.split("/by", 2);
        String description = split[0].trim();
        String by = split.length > 1 ? split[1].trim() : "";
        
        if (description.isEmpty() || by.isEmpty()) {
            throw new LyraException("Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>");
        }
        
        return new AddDeadlineCommand(description, by);
    }
    
    private static Command parseEventCommand(String arguments) throws LyraException {
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>");
        }
        
        String[] splitFrom = arguments.split("/from", 2);
        String description = splitFrom[0].trim();
        String from = "";
        String to = "";
        
        if (splitFrom.length > 1) {
            String[] splitTo = splitFrom[1].split("/to", 2);
            from = splitTo[0].trim();
            if (splitTo.length > 1) {
                to = splitTo[1].trim();
            }
        }
        
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new LyraException("Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>");
        }
        
        return new AddEventCommand(description, from, to);
    }
    
    private static Command parseMarkCommand(String arguments) throws LyraException {
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Invalid task number.");
        }
        
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new LyraException("Invalid task number.");
        }
    }
    
    private static Command parseUnmarkCommand(String arguments) throws LyraException {
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Invalid task number.");
        }
        
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new LyraException("Invalid task number.");
        }
    }
    
    private static Command parseDeleteCommand(String arguments) throws LyraException {
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Please specify a task number to delete. Try: delete <task_number>");
        }
        
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new LyraException("Invalid task number.");
        }
    }
}
