import java.util.Scanner;

public class Lyra {
    public static void main(String[] args) {
        final String line = "____________________________________________________________";

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

            System.out.println(line);
            System.out.println(" " + input);
            System.out.println(line);
        }
        scanner.close();
    }
}


