import java.util.HashMap;
public class ConqueredShips { // responsible for managing the player's ship states
    private final HashMap<String, Integer> conqueredShips = new HashMap<>();

    public ConqueredShips() {
        for (ShipType shipName : ShipType.values()) {
            conqueredShips.put(shipName.name(), 0);
        }
    }

    public void displayCS() {
        System.out.println("Ships conquered:"+ "\n \t"+ conqueredShips);
    }

    public int countCS() {
        return conqueredShips.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void addCS(Ship ship) {
        conqueredShips.compute(ship.getShipType().name(), (key, value) -> value == null ? 1 : value + 1);
    }
}
