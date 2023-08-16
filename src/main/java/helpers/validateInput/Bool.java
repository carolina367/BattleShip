package helpers.validateInput;

import java.util.Scanner;

public class Bool {
    public static boolean getBool(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            if(Boolean.parseBoolean(input) || "t".equalsIgnoreCase(input)) {
                return true;
            } else if ("f".equalsIgnoreCase(input)) {
                return false;
            } else {
                System.out.println(">>>> This input is not a boolean - Please try again!");
            }
        }
    }
}
