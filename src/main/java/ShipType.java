import java.util.Arrays;
import java.util.Optional;

public enum ShipType{

    CARRIER("cs", 5), // the number is the length
    BATTLESHIP("bs", 4),
    CRUISER("cr", 3),
    DESTROYER("de", 2);

    private final String abbr;
    private final int length;

    ShipType(String key, int length) {
        this.abbr = key;
        this.length = length;
    }

    public String getAbbr() {
        return abbr;
    }

    public int getLength() {
        return length;
    }

//    // Reverse lookup methods
//    public static Optional<ShipType> getShipTypeByValue(String value) { // todo: figure out if i need to delete
//        return Arrays.stream(ShipType.values())
//                .filter(ship -> ship.abbr.equalsIgnoreCase(value)) // need .equals for strings
//                .findFirst();
//    }
//
//    public static Optional<ShipType> getShipTypeByValue(int value) {
//        return Arrays.stream(ShipType.values())
//                .filter(ship -> ship.length == value)
//                .findFirst();
//    }
    public String toString() {
        String id = name(); // BATTLESHIP
        String lower = id.substring(1).toLowerCase(); // ATTLESHIP -> attleship
        return id.charAt(0) + lower; // B + attleship
    }
}