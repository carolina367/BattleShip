import java.util.Arrays;
import java.util.Optional;

public enum ShipType{

    CARRIER("cs", 5), // the number is the length
    BATTLESHIP("bs", 4),
    CRUISER("cr", 3),
    DESTROYER("de", 2);

    private final String key;
    private final int length;

    ShipType(String key, int length) {
        this.key = key;
        this.length = length;
    }

    public String getKey() {
        return key;
    }

    public int getLength() {
        return length;
    }

    // Reverse lookup methods
    public static Optional<ShipType> getShipTypeByValue(String value) { // todo: figure out if i need to delete
        return Arrays.stream(ShipType.values())
                .filter(ship -> ship.key.equalsIgnoreCase(value)) // need .equals for strings
                .findFirst();
    }

    public static Optional<ShipType> getShipTypeByValue(int value) {
        return Arrays.stream(ShipType.values())
                .filter(ship -> ship.length == value)
                .findFirst();
    }
    public String toString() {
        return key;
        // todo: make this for the lowercase
//        String id = name(); // BATTLESHIP
//        String lower = id.substring(1).toLowerCase(); // ATTLESHIP -> attleship
//        return id.charAt(0) + lower; // B + attleship
    }
}