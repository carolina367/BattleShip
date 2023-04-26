public class Tile {
    private TileTypes type;
    private String key;
    private Ship ship;

    public Tile() {
        type = TileTypes.WATER;
        key = TileTypes.WATER.toString();
        ship = null;
    }

    public String getKey() {
        return key;
    }

    public Ship getShip() {
        return ship;
    }

    public TileTypes getTileType() {
        return type;
    }

    public void setTileType(TileTypes typeDeclared) {
        this.key = typeDeclared.toString();
        this.type = typeDeclared;

    }

    public void setTileType(TileTypes typeDeclared, Ship ship) {
        this.key = typeDeclared.toString();
        this.type = typeDeclared;
        if (typeDeclared == TileTypes.BOMBED_ROCK) { // wil create an interface for obstacle and change ship to that
        } else if (typeDeclared == TileTypes.BOMBED_SHIP) {
        } else if (typeDeclared == TileTypes.COVERED_SHIP) {
            this.ship = ship;
        } else if (typeDeclared == TileTypes.COVERED_ROCK) {
        } else if (typeDeclared == TileTypes.UNCOVERED_SHIP) {
        } else if (typeDeclared == TileTypes.UNCOVERED_ROCK) {
        } // there really should never be a need to set tile back to water

    }
}