import java.util.Scanner;
import java.util.ArrayList;

public class Lyra {
    public static void main(String[] args) {
        final String line = "____________________________________________________________";
        final ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(line);
        System.out.println(" Hello! I'm Lyra");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String trimmedInput = input.trim();

            System.out.println();
            if (trimmedInput.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            if (trimmedInput.isEmpty()) {
                System.out.println(line);
                System.out.println(" Please enter a command. Type 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete', or 'bye'.");
                System.out.println(line);
                continue;
            }

            if (trimmedInput.equalsIgnoreCase("list")) {
                System.out.println(line);
                if (tasks.isEmpty()) {
                    System.out.println(" There are no tasks in your list.");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
                    }
                }
                System.out.println(line);
            } else if (trimmedInput.toLowerCase().startsWith("mark ")) {
                String[] parts = trimmedInput.split(" ", 2);
                if (parts.length == 2) {
                    try {
                        int idx = Integer.parseInt(parts[1]) - 1;
                        if (idx >= 0 && idx < tasks.size()) {
                            tasks.get(idx).markAsDone();
                            System.out.println(line);
                            System.out.println(" Nice! I've marked this task as done:");
                            System.out.println("   " + tasks.get(idx).toString());
                            System.out.println(line);
                        } else {
                            System.out.println(line);
                            System.out.println(" Invalid task number.");
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(line);
                        System.out.println(" Invalid task number.");
                        System.out.println(line);
                    }
                }
            } else if (trimmedInput.toLowerCase().startsWith("unmark ")) {
                String[] parts = trimmedInput.split(" ", 2);
                if (parts.length == 2) {
                    try {
                        int idx = Integer.parseInt(parts[1]) - 1;
                        if (idx >= 0 && idx < tasks.size()) {
                            tasks.get(idx).markAsNotDone();
                            System.out.println(line);
                            System.out.println(" OK, I've marked this task as not done yet:");
                            System.out.println("   " + tasks.get(idx).toString());
                            System.out.println(line);
                        } else {
                            System.out.println(line);
                            System.out.println(" Invalid task number.");
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(line);
                        System.out.println(" Invalid task number.");
                        System.out.println(line);
                    }
                }
            } else if (trimmedInput.toLowerCase().startsWith("delete ")) {
                String[] parts = trimmedInput.split(" ", 2);
                if (parts.length == 2) {
                    try {
                        int idx = Integer.parseInt(parts[1]) - 1;
                        if (idx >= 0 && idx < tasks.size()) {
                            Task removedTask = tasks.remove(idx);
                            System.out.println(line);
                            System.out.println(" Noted. I've removed this task:");
                            System.out.println("   " + removedTask.toString());
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println(line);
                        } else {
                            System.out.println(line);
                            System.out.println(" Invalid task number.");
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(line);
                        System.out.println(" Invalid task number.");
                        System.out.println(line);
                    }
                } else {
                    System.out.println(line);
                    System.out.println(" Please specify a task number to delete. Try: delete <task_number>");
                    System.out.println(line);
                }
            } else if (trimmedInput.equalsIgnoreCase("todo") || trimmedInput.toLowerCase().startsWith("todo ")) {
                String description = trimmedInput.length() == 4 ? "" : input.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println(line);
                    System.out.println(" Sorry, a todo needs a description. Try: todo <description>");
                    System.out.println(line);
                    continue;
                }
                Task task = new Todo(description);
                tasks.add(task);
                System.out.println(line);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task.toString());
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(line);
            } else if (trimmedInput.equalsIgnoreCase("deadline") || trimmedInput.toLowerCase().startsWith("deadline ")) {
                String rest = trimmedInput.length() == 8 ? "" : input.substring(9).trim();
                if (rest.isEmpty()) {
                    System.out.println(line);
                    System.out.println(" Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>");
                    System.out.println(line);
                    continue;
                }
                String[] split = rest.split("/by", 2);
                String description = split[0].trim();
                String by = split.length > 1 ? split[1].trim() : "";
                if (description.isEmpty() || by.isEmpty()) {
                    System.out.println(line);
                    System.out.println(" Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>");
                    System.out.println(line);
                    continue;
                }
                Task task = new Deadline(description, by);
                tasks.add(task);
                System.out.println(line);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task.toString());
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(line);
            } else if (trimmedInput.equalsIgnoreCase("event") || trimmedInput.toLowerCase().startsWith("event ")) {
                String rest = trimmedInput.length() == 5 ? "" : input.substring(6).trim();
                if (rest.isEmpty()) {
                    System.out.println(line);
                    System.out.println(" Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>");
                    System.out.println(line);
                    continue;
                }
                String[] splitFrom = rest.split("/from", 2);
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
                    System.out.println(line);
                    System.out.println(" Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>");
                    System.out.println(line);
                    continue;
                }
                Task task = new Event(description, from, to);
                tasks.add(task);
                System.out.println(line);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task.toString());
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println(" Sorry, I couldn't recognize that command. Try one of: list, todo, deadline, event, mark, unmark, delete, bye.");
                System.out.println(line);
            }
        }
        scanner.close();
    }
}


