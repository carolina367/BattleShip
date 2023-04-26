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

    public void setTileType(TileType typeDeclared) { // for transitioning tile
        if (typeDeclared != TileType.COVERED_SHIP && typeDeclared != TileType.COVERED_ROCK) {
            this.key = typeDeclared.toString();
            this.type = typeDeclared;
        } else {
            System.out.println("tried to use SetTileType() on CS or BS without giving function a ship");
        }
    }

    public void setTileType(TileType typeDeclared, Ship ship) {  // for initializing a tile with obstacle or ship
        if (typeDeclared == TileType.COVERED_SHIP || typeDeclared == TileType.COVERED_ROCK) {
            this.ship = ship;
//          this.obstacle = rock; // TODO: create an interface for obstacle and change ship to that
            this.key = typeDeclared.toString();
            this.type = typeDeclared;
        }
    }
}