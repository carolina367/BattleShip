public class Player {
    private final String name;
    public Player(String name) {
        this.name = name;
    }

    private final int conqueredEnemyShips = 0;
    private final int shipsAlive=10;
    public int howManyShipsAlive() {
        return shipsAlive;
    }
    public boolean playerLost() {
        return shipsAlive == 0;
    }
    public String getName() {
        return name;
    }

    public void Player(){

    }
}
