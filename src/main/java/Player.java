public class Player {
    private final String name;
    public Player(String name) {
        this.conqueredEnemyShips = 0;
        this.name = name;
    }

    private int conqueredEnemyShips;
    private int shipsAlive;
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
