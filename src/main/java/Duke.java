import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while(true) {
            String userResponse = sc.nextLine();

            if (userResponse.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(userResponse);
        }
    }
}
