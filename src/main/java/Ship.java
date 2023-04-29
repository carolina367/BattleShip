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

    public int getHitsTaken() {
        return hitsTaken;
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 84288473a7c4e547e19570f2291b0c02ff3084bc
