import java.util.HashMap;
import java.util.Scanner;

public class Human extends Player {
    private String name;
    private final HashMap<String, Integer> conqueredShips  = new MyHashMap<>();

    public Human() {
        super();
    }

    public Human(String name) {
        super(name);
    }

    public String getName(){
        return name;
    }

    public void setName() {
       Scanner sc = new Scanner(System.in);
        String playerName = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter player name (2 characters or more, only alpha-numeric characters but cannot only be numeric): ");
            playerName = sc.nextLine();
            if (playerName.length() < 2) {
                System.out.println("Name must be at least 2 characters long.");
            } else if (!playerName.matches("[a-zA-Z0-9]+")) {
                System.out.println("Name can only contain alpha-numeric characters.");
            } else if (playerName.matches("[0-9]+")) {
                System.out.println("Name cannot only contain numeric characters.");
            } else {
                isValid = true;
            }
        }
        this.name = playerName;
    }

    public void addConqueredShips(Ship ship) {
        super.addConqueredShips(ship);
    }
}
