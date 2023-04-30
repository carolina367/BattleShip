import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Player {
    private String name;
    private HashMap<String, Integer> conqueredShips = new HashMap<>();

    private int prevX;
    private int prevY;

    public Player() {
        this.name = "";
        conqueredShips.put("CARRIER", 0);
        conqueredShips.put("BATTLESHIP", 0);
        conqueredShips.put("CRUISER", 0);
        conqueredShips.put("DESTROYER", 0);
    }

    public Player(String name) {
        this.name = name;
        conqueredShips.put("CARRIER", 0);
        conqueredShips.put("BATTLESHIP", 0);
        conqueredShips.put("CRUISER", 0);
        conqueredShips.put("DESTROYER", 0);
    }

    public String getName() {
        return name;
    }

    public abstract void setName();

    public void displayConqueredShips() {
        System.out.println("Ships conquered:");
        for (String i : conqueredShips.keySet()) {
            System.out.println("\t" + i.toLowerCase() + ": " + conqueredShips.get(i));
        }
    }

    public int countConqueredShips() {
        int sumOfShips = 0;
        for (String i : conqueredShips.keySet()) {
            sumOfShips += conqueredShips.get(i);
        }
        return sumOfShips;
    }

    public void addConqueredShips(Ship ship) {
        conqueredShips.put(ship.getShipType().name(), conqueredShips.get(ship.getShipType().name()) + 1);
    }

    public abstract void turn(Board board);
}
