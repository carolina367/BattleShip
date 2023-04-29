public abstract class Obstacle {
    int length;
    int hitsTaken;
    boolean vertical;
    boolean isSunk;

    public Obstacle() {
        isSunk = false;
        hitsTaken = 0;
    }
    public Obstacle(boolean verticalDeclared) {
        vertical = verticalDeclared;
        hitsTaken = 0;
        isSunk = false;
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

}
