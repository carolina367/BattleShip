public enum ShipType{

    CARRIER("cs"),
    BATTLESHIP("bs"),
    CRUISER("cr"),
    DESTROYER("de");

    private String extendedName;

    ShipType(String extendedName) {
        this.extendedName = extendedName;
    }

    public String toString() {
        return extendedName;
    }
}