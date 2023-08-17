import helpers.MyHashMap;

import java.util.HashMap;
public class ConqueredShips { // responsible for managing the player's ship states
    private final MyHashMap<String, Integer> conqueredShips = new MyHashMap<>();

    public ConqueredShips() {
        for (ShipType shipName : ShipType.values()) { // inits all ships to 0
            conqueredShips.put(shipName.name(), 0);
        }
    }

    public void displayCS() {
        System.out.println("Ships conquered:"+ "\n \t"+ conqueredShips);
    }

    public int countCS() {
        return conqueredShips.sumAll();
    }

    public void addCS(Ship ship) {
        conqueredShips.compute(ship.getShipType().name(), (key, value) -> value == null ? 1 : value + 1);
    }
}
