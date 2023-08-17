public enum TileState {
    WATER(" W"), //0
    BOMBED_WATER("BW"), //1

    COVERED_ROCK("CR"), //3
    BOMBED_ROCK("BR"), //4
    UNCOVERED_ROCK("UR"), //5

    COVERED_SHIP("CS"), //6
    BOMBED_SHIP("BS"), //7
    UNCOVERED_SHIP("US"); //8

    private final String abbr; // abbreviation

    TileState(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() { return abbr; }

    public String toString() {
        String id = name(); // BATTLESHIP
        String lower = id.substring(1).toLowerCase(); // ATTLESHIP -> attleship
        return id.charAt(0) + lower; // B + attleship
    }
}