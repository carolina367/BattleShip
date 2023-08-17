public interface Obstacle {
    public int getLength();
    public int getHitsTaken();
    public boolean getIsSunk();
    public void setVertical(boolean vertical);
    public void setSunk();
    public void takeHit();
    public boolean isVertical();

}
