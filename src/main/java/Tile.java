public class Tile {
    private TileType type;
    private String key;
    private Ship ship;

    public Tile() {
        this.type = TileType.WATER;
        this.key = TileType.WATER.toString();
        this.ship = null;
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
        if(this.type == TileType.COVERED_SHIP && typeDeclared == TileType.WATER) { // setting back to water
            this.ship = null;
        }
        if (typeDeclared != TileType.COVERED_SHIP && typeDeclared != TileType.COVERED_ROCK) {
            this.key = typeDeclared.toString();
            this.type = typeDeclared;
        } else {
            System.out.println("You need to enter a ship to set a tile to type CS");
        }
    }

    public void setTileType(TileType typeDeclared, Ship ship) {  // for initializing a tile with obstacle or ship
        if (typeDeclared == TileType.COVERED_SHIP || typeDeclared == TileType.COVERED_ROCK) {
            this.key = typeDeclared.toString();
            this.type = typeDeclared;
            this.ship = ship;
//          this.obstacle = obst; // TODO: create an interface for obstacle and change ship to that
        }
    }
}