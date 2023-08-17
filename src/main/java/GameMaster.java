import helpers.MyHashMap;

public class GameMaster { // store info about the game and what type it is
    static Game myGame = GameFactory.createGame(GameFactory.GameTypes.STANDARD);
    static int boardSize = myGame.getBoardSize();
    static String gameTypeName = StandardGame.getStaticGameType();
    static AdvancedFeature advancedFeature = StandardGame.getStaticAdvancedFeature();
    static MyHashMap<String, Integer> ships = StandardGame.getStaticShipsToPlace();

    private static GameMaster instance;
    private GameMaster() {}

//    public static int getBoardSize() {
//        return boardSize;
//    }

    public static String getGameTypeName() {
        return gameTypeName;
    }

    public static MyHashMap<String, Integer> getShips() {
        return ships;
    }

    public static AdvancedFeature getFeature() {
        return advancedFeature;
    }

    public static Game getMyGame() {
        return myGame;
    }







}
