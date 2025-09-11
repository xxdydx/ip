package lyra.util;

import lyra.command.Command;
import lyra.command.ExitCommand;
import lyra.command.ListCommand;
import lyra.command.AddTodoCommand;
import lyra.command.AddDeadlineCommand;
import lyra.command.AddEventCommand;
import lyra.command.MarkCommand;
import lyra.command.UnmarkCommand;
import lyra.command.DeleteCommand;
import lyra.command.FindCommand;
import lyra.exception.LyraException;

/**
 * Utility class for parsing user input commands and converting them to Command objects.
 */
public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    
    private static final String DEADLINE_SEPARATOR = "/by";
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";
    
    /**
     * Parses a full command string and returns the appropriate Command object.
     */
    public static Command parse(String fullCommand) throws LyraException {
        String trimmedCommand = fullCommand.trim();
        
        if (trimmedCommand.isEmpty()) {
            throw new LyraException("Please enter a command. Type 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete', or 'bye'.");
        }
        
        String[] parts = trimmedCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        
        switch (command) {
            case COMMAND_BYE:
                return new ExitCommand();
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_TODO:
                return parseTodoCommand(arguments);
            case COMMAND_DEADLINE:
                return parseDeadlineCommand(arguments);
            case COMMAND_EVENT:
                return parseEventCommand(arguments);
            case COMMAND_MARK:
                return parseMarkCommand(arguments);
            case COMMAND_UNMARK:
                return parseUnmarkCommand(arguments);
            case COMMAND_DELETE:
                return parseDeleteCommand(arguments);
            case COMMAND_FIND:
                return parseFindCommand(arguments);
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
        
        String[] split = arguments.split(DEADLINE_SEPARATOR, 2);
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
        
        String[] splitFrom = arguments.split(EVENT_FROM_SEPARATOR, 2);
        String description = splitFrom[0].trim();
        String from = "";
        String to = "";
        
        if (splitFrom.length > 1) {
            String[] splitTo = splitFrom[1].split(EVENT_TO_SEPARATOR, 2);
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

    private static Command parseFindCommand(String arguments) throws LyraException {
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Please provide a keyword to find. Try: find <keyword>");
        }
        return new FindCommand(arguments.trim());
    }
}
