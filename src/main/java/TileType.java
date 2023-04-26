public enum TileType {
    WATER(" W"), //0
    BOMBED_WATER("BW"), //1
    BOMBED_ROCK("BR"), //2
    BOMBED_SHIP("BS"), //3
    COVERED_SHIP("CS"), //4
    COVERED_ROCK("CR"), //5
    UNCOVERED_SHIP("US"), //6
    UNCOVERED_ROCK("UR"); //7

    private String extendedName;

    TileType(String extendedName) {
        this.extendedName = extendedName;
    }

    public String toString() {
        return extendedName;
    }
}