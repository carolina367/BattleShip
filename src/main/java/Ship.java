public class Ship {
    private int length;
    private int numberOfHitsGot;
    private boolean vertical;
    private boolean isSunk;
    private ShipType type;

    public Ship(){
        isSunk = false;
        numberOfHitsGot = 0;
    }
    public Ship(boolean verticalDeclared, ShipType typeDeclared){
         vertical = verticalDeclared;
         numberOfHitsGot = 0;
         isSunk = false;
         type = typeDeclared;
         if (typeDeclared == ShipType.CARRIER){length = 5;}
         else if (typeDeclared == ShipType.BATTLESHIP){length = 4;}
         else if (typeDeclared == ShipType.CRUISER){length = 3;}
         else if (typeDeclared == ShipType.DESTROYER){length = 2;}
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNumberOfHitsGot() {
        return numberOfHitsGot;
    }

    public void setNumberOfHitsGot(int numberOfHitsGot) {
        this.numberOfHitsGot = numberOfHitsGot;
        if(this.numberOfHitsGot == this.length) {
            this.setSunk(true);
        }
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    public ShipType getShipType() {
        return type;
    }

    public void setShipType(ShipType typeDeclared) {
        if (typeDeclared == ShipType.CARRIER){length = 5;}
        else if (typeDeclared == ShipType.BATTLESHIP){length = 4;}
        else if (typeDeclared == ShipType.CRUISER){length = 3;}
        else if (typeDeclared == ShipType.DESTROYER){length = 2;}
        this.type = typeDeclared;
    }



}
