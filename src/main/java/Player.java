import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private final String name;
    private final HashMap<String, Integer> conqueredShips;
    ////////////AI///////////
    Player AI= new Player("AI");
    private boolean isNowCurrentPlayerAI=false;
    private boolean changesDoneInThisTurnAI=false;
    private int conqueredShipsByAI=0;
    public int[][] boardAI=new int[10][10];
    //null=not bombed,1-water,2bombed,3bombed and sunk
    ////////////AI///////////

    public Player(String name) {
        this.name = name;
        conqueredShips = new HashMap<>();
        conqueredShips.put("CARRIER", 0);
        conqueredShips.put("BATTLESHIP", 0);
        conqueredShips.put("CRUISER", 0);
        conqueredShips.put("DESTROYER", 0);
    }

    public String getName() {
        return name;
    }

    public void displayConqueredShips() {
        System.out.println("Ships conquered:");
        for (String i : conqueredShips.keySet()) {
            System.out.println("\t" + i.toLowerCase() + ": " + conqueredShips.get(i));
        }
    }

    public int countConqueredShips() {
        int sumOfShips = 0;
        for (String i : conqueredShips.keySet()) {
            sumOfShips += conqueredShips.get(i);
        }
        return sumOfShips;
    }

    public void addConqueredShips(Ship ship) {
        conqueredShips.put(ship.getShipType().name(), conqueredShips.get(ship.getShipType().name()) + 1);
        if (isNowCurrentPlayerAI==true){
            for (int i=0;i>10;i++){
                for (int j=0;j>10;j++){
                    if (boardAI[i][j]==2){
                        boardAI[i][j]=3;
                    }
                }
            }
        }
    }

    public void AI() {

        int x;
        int y = 0;
        for(int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if (boardAI[i][j]==2){
                    if (i==0 && changesDoneInThisTurnAI==false){i++;x=i;y=j;changesDoneInThisTurnAI=true;}
                    else if (i==9 && changesDoneInThisTurnAI==false){i--;x=i;y=j;changesDoneInThisTurnAI=true;}
                    else if (j==0 && changesDoneInThisTurnAI==false){j++;x=i;y=j;changesDoneInThisTurnAI=true;}
                    else if (j==9 && changesDoneInThisTurnAI==false){j--;x=i;y=j;changesDoneInThisTurnAI=true;}
                }
            }
        }
        if (changesDoneInThisTurnAI==false){
            x = ThreadLocalRandom.current().nextInt(0, 10);
            y = ThreadLocalRandom.current().nextInt(0, 10);
            while (boardAI[x][y]==1) {
                x = ThreadLocalRandom.current().nextInt(0, 10);
                y = ThreadLocalRandom.current().nextInt(0, 10);
            }
//            if (gameBoard.getTileType(x,y) != TileType.WATER){
//                boardAI[x][y]=2;
//            } else {boardAI[x][y]=1;}

        }
    }
    public boolean isNowCurrentPlayerAI() {
        return isNowCurrentPlayerAI;
    }

    public int[][] getBoardAI() {
        return boardAI;
    }
}