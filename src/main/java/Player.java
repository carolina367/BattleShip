import java.util.HashMap;

public class Player {
    private final String name;
    private final MyHashMap<String, Integer> conqueredShips;

    public Player(String name) {
        this.name = name;
        conqueredShips = new MyHashMap<>();
        conqueredShips.put("CARRIER", 0);
        conqueredShips.put("BATTLESHIP", 0);
        conqueredShips.put("CRUISER", 0);
        conqueredShips.put("DESTROYER", 0);
    }

    public String getName() {
        return name;
    }

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
}
