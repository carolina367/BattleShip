public class Tile {
    private TileType type;
    private String key;
    private Ship ship;

    public Tile() {
        type = TileType.WATER;
        key = TileType.WATER.toString();
        ship = null;
    }

    public String getKey() {
        return key;
    }

    public Ship getShip() {
        return ship;
    }

    public TileType getTileType() {
        return type;
    }

    public void setTileType(TileType typeDeclared) {
        this.key = typeDeclared.toString();
        this.type = typeDeclared;
    }

    public void setTileType(TileType typeDeclared, Ship ship) {
        this.key = typeDeclared.toString();
        this.type = typeDeclared;
        if (typeDeclared == TileType.COVERED_SHIP) {
            this.ship = ship;
        }
//        if (typeDeclared == TileType.BOMBED_ROCK) { // wil create an interface for obstacle and change ship to that
//        } else if (typeDeclared == TileType.BOMBED_SHIP) {
//        } else if (typeDeclared == TileType.COVERED_ROCK) {
//        } else if (typeDeclared == TileType.UNCOVERED_SHIP) {
//        } else if (typeDeclared == TileType.UNCOVERED_ROCK) {
//        } // there really should never be a need to set tile back to water

    }
    public void bombing(int x, int y){
        if (this.type==TileTypes.WATER){
            setTileType(TileTypes.BOMBED_WATER);
        }
        else if (this.type==TileTypes.UNCOVERED_ROCK) {
            setTileType(TileTypes.BOMBED_ROCK);
        }
        else if (this.type==TileTypes.UNCOVERED_SHIP) {
            setTileType(TileTypes.BOMBED_SHIP);
        }
        else if (this.type==TileTypes.COVERED_SHIP) {
            setTileType(TileTypes.BOMBED_SHIP);
        }

    }
}