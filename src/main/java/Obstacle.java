public abstract class Obstacle {
    int length;
    int hitsTaken;
    boolean isVertical;
    boolean isSunk;

    public Obstacle() {
        isSunk = false;
        isVertical = false;
        hitsTaken = 0;
        length = 0;
    }
    public Obstacle(boolean verticalDeclared) {
        isSunk = false;
        isVertical = verticalDeclared;
        hitsTaken = 0;
        length = 0;
    }

    public int getLength() {
        return length;
    }

    public int getHitsTaken() {
        return hitsTaken;
    }

    public boolean getIsSunk() {
        return isSunk;
    }

    public void setVertical(boolean vertical) {
        this.isVertical = vertical;
    }

    public void setSunk() {
        isSunk = true;
    }

    public void takeHit() {
        hitsTaken++;
        if (hitsTaken == length) {
            this.setSunk();
        }
    }

    public boolean isVertical() {
        return isVertical;
    }

}
