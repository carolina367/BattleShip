public class Player {
    private String name;
    public Player(String name) {
        this.name = name;
    }
    public int whoseTurnIsIt=1;
    public void switchTurns(){
        if(whoseTurnIsIt==1){
            whoseTurnIsIt=0;
        }
        else {
            whoseTurnIsIt = 1;
        }
    }

    public int getWhoseTurnIsIt(){return whoseTurnIsIt;}
    private int shipsLeftToPlace=10;
    private int conqueredEnemyShips = 0;
    private int shipsAlive=10;
    public int howManyShipsAlive() {
        return shipsAlive;
    }
    public int getShipsLeftToPlace(){return shipsLeftToPlace;}
    public boolean playerLost() {
        return shipsAlive == 0;
    }
    public String getName() {
        return name;
    }

    public void Player(){

    }




}