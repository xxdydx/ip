package duke.util;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Lyra");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "bye"; // Exit gracefully if no more input
        }
    }

    public void showEmptyCommandMessage() {
        showLine();
        System.out.println(" Please enter a command. Type 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete', or 'bye'.");
        showLine();
    }

    public void showUnknownCommandMessage() {
        showLine();
        System.out.println(" Sorry, I couldn't recognize that command. Try one of: list, todo, deadline, event, mark, unmark, delete, bye.");
        showLine();
    }

    public void showTaskList(ArrayList<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println(" There are no tasks in your list.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        showLine();
    }

    public void showMatchingTasks(ArrayList<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        showLine();
    }

    public void showTaskAdded(Task task, int totalTasks) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    public void showTaskMarked(Task task, boolean isDone) {
        showLine();
        if (isDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task.toString());
        showLine();
    }

    public void showTaskDeleted(Task task, int remainingTasks) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
        showLine();
    }

    public void showInvalidTaskNumber() {
        showLine();
        System.out.println(" Invalid task number.");
        showLine();
    }

    public void showTodoErrorMessage() {
        showLine();
        System.out.println(" Sorry, a todo needs a description. Try: todo <description>");
        showLine();
    }

    public void showDeadlineErrorMessage() {
        showLine();
        System.out.println(" Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>");
        showLine();
    }

    public void showEventErrorMessage() {
        showLine();
        System.out.println(" Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>");
        showLine();
    }

    public void showDeleteErrorMessage() {
        showLine();
        System.out.println(" Please specify a task number to delete. Try: delete <task_number>");
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println(" Error loading tasks from file. Starting with empty task list.");
        showLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println(" " + message);
        showLine();
    }

    public void close() {
        scanner.close();
    }
}
