public class Tile {
   private TileTypes type;
   private String key;

    public Tile() {
        type = TileTypes.WATER;
        key = " W";
    }

    public String getKey() {
        return key;
    }

    public TileTypes getTileType() {
        return type;
    }

    public void setTileType(TileTypes typeDeclared) {
        if (typeDeclared == TileTypes.BOMBED_WATER){this.key = "BW";}
        else if (typeDeclared == TileTypes.BOMBED_ROCK){this.key = "BR";}
        else if (typeDeclared == TileTypes.BOMBED_SHIP){this.key = "BS";}
        else if (typeDeclared == TileTypes.COVERED_SHIP){this.key = "CS";}
        else if (typeDeclared == TileTypes.COVERED_ROCK){this.key = "CR";}
        else if (typeDeclared == TileTypes.UNCOVERED_SHIP){this.key = "US";}
        else if (typeDeclared == TileTypes.UNCOVERED_ROCK){this.key = "UR";}
        else if (typeDeclared == TileTypes.WATER){this.key = "W";} // there really should never be a need to set something back to water
        this.type = typeDeclared;

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