public class Ship {
    private int length;
    private int hitsTaken;
    private boolean vertical;
    private boolean isSunk;
    private ShipType type;

    public Ship() {
        isSunk = false;
        hitsTaken = 0;
    }

    public Ship(boolean verticalDeclared, ShipType typeDeclared) {
        vertical = verticalDeclared;
        hitsTaken = 0;
        isSunk = false;
        type = typeDeclared;
        if (typeDeclared == ShipType.CARRIER) {
            length = 5;
        } else if (typeDeclared == ShipType.BATTLESHIP) {
            length = 4;
        } else if (typeDeclared == ShipType.CRUISER) {
            length = 3;
        } else if (typeDeclared == ShipType.DESTROYER) {
            length = 2;
        }
    }

    public int getLength() {
        return length;
    }

    public void takeHit() {
        hitsTaken++;
        if (hitsTaken == length) {
            this.setSunk();
        }
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean getIsSunk() {
        return isSunk;
    }

    public void setSunk() {
        isSunk = true;
    }

    public ShipType getShipType() {
        return type;
    }

    public void setShipType(ShipType typeDeclared) {
        if (typeDeclared == ShipType.CARRIER) {
            length = 5;
        } else if (typeDeclared == ShipType.BATTLESHIP) {
            length = 4;
        } else if (typeDeclared == ShipType.CRUISER) {
            length = 3;
        } else if (typeDeclared == ShipType.DESTROYER) {
            length = 2;
        }
        type = typeDeclared;
    }

    public int[] findShipStart(int letter, int num, Board gameBoard) {
        int[] coords = {letter, num}; // if none are found it'll return this
        for (int i = 0; i < length + 1; i++) { // at most, we are at the end of the ship
            int letterOffset = isVertical() ? i : 0;
            int numOffset = isVertical() ? 0 : i;
            if (outOfBounds((letter - letterOffset), (num - numOffset), gameBoard.getSize()) || gameBoard.getTile((letter - letterOffset), (num - numOffset)).getShip() != this) {
                coords[0] = letter - letterOffset + (letterOffset != 0 ? 1 : 0);
                coords[1] = num - numOffset + (numOffset != 0 ? 1 : 0);
                break;
            }
        }
        return coords;
    }

    public boolean outOfBounds(int letter, int num, int size) {
        for (int i = 0; i < length; i++) { // checking that the ship isn't overlapping any coordinate
            int letterOffset = isVertical() ? i : 0;
            int numOffset = isVertical() ? 0 : i;
            if ((letter + letterOffset) >= size || (num + numOffset) >= size || num < 0 || letter < 0) {
                return true; // coordinates are out of bounds
            }
        }
        return false; // coordinates are within bounds
    }

    public void setAllTilesInShip(int letter, int num, Board gameBoard, TileType type) { // the coords need to be to the start of the ship
        // ensure coords are for the start of the ship
        if (type != TileType.COVERED_SHIP) {
            letter = findShipStart(letter, num, gameBoard)[0];
            num = findShipStart(letter, num, gameBoard)[1];
        }

        for (int i = 0; i < length; i++) { //uncovering the ship on every coordinate
            int letterOffset = vertical ? i : 0;
            int numOffset = vertical ? 0 : i;
            if (type == TileType.COVERED_SHIP) {
                gameBoard.getTile((letter + letterOffset), (num + numOffset)).setTileType(type, this);
            } else {
                gameBoard.getTile((letter + letterOffset), (num + numOffset)).setTileType(type);
                // handled set back to water in tile - essentially a remove ship func
            }
        }
    }
}
