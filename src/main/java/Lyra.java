import java.util.Scanner;

public class Lyra {
    public static void main(String[] args) {
        final String line = "____________________________________________________________";
        final Task[] tasks = new Task[100];
        int taskCount = 0;

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

            if (trimmedInput.equalsIgnoreCase("list")) {
                System.out.println(line);
                if (taskCount == 0) {
                    System.out.println(" There are no tasks in your list.");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i].toString());
                    }
                }
                System.out.println(line);
            } else if (trimmedInput.toLowerCase().startsWith("mark ")) {
                String[] parts = trimmedInput.split(" ", 2);
                if (parts.length == 2) {
                    try {
                        int idx = Integer.parseInt(parts[1]) - 1;
                        if (idx >= 0 && idx < taskCount) {
                            tasks[idx].markAsDone();
                            System.out.println(line);
                            System.out.println(" Nice! I've marked this task as done:");
                            System.out.println("   " + tasks[idx].toString());
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
                        if (idx >= 0 && idx < taskCount) {
                            tasks[idx].markAsNotDone();
                            System.out.println(line);
                            System.out.println(" OK, I've marked this task as not done yet:");
                            System.out.println("   " + tasks[idx].toString());
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
            } else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = new Task(input);
                    taskCount++;
                }
                System.out.println(line);
                System.out.println(" added: " + input);
                System.out.println(line);
            }
        }
        scanner.close();
    }
}


