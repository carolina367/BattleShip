package helpers.validateInput;

import java.util.Scanner;

public class Name {
    public static String getName() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter player name (2 characters or more, only alpha-numeric characters but cannot only be numeric): ");
            String playerName = sc.nextLine();

            if (isValidName(playerName)) {
                return playerName;
            }
        }
    }
    private static boolean isValidName(String playerName) {
        if (playerName.length() < 2) {
            System.out.println("Name must be at least 2 characters long.");
            return false;
        }
        if (!playerName.matches("[a-zA-Z0-9]+")) {
            System.out.println("Name can only contain alpha-numeric characters.");
            return false;
        }
        if (playerName.matches("[0-9]+")) {
            System.out.println("Name cannot only contain numeric characters.");
            return false;
        }
        if (playerName.equalsIgnoreCase("AI")) {
            System.out.println("You cannot name yourself 'AI'.");
            return false;
        }
        return true;
    }
}
