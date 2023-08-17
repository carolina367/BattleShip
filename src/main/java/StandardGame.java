import helpers.MyHashMap;
public class StandardGame implements Game {
    public static final int BOARD_SIZE = 10;
    public static final String GAME_TYPE = "standard";
    public static final AdvancedFeature ADVANCED_FEATURE = AdvancedFeature.NONE;
    public static final MyHashMap<String, Integer> SHIPS_TO_PLACE = new MyHashMap<>();

    static {
        SHIPS_TO_PLACE.put("CARRIER", 1);
        SHIPS_TO_PLACE.put("BATTLESHIP", 1);
        SHIPS_TO_PLACE.put("CRUISER", 2);
        SHIPS_TO_PLACE.put("DESTROYER", 1);
    }

    // Static getters
    public static int getStaticBoardSize() {
        return BOARD_SIZE;
    }

    public static String getStaticGameType() {
        return GAME_TYPE;
    }

    public static MyHashMap<String, Integer> getStaticShipsToPlace() {
        return SHIPS_TO_PLACE;
    }

    public static AdvancedFeature getStaticAdvancedFeature() {
        return ADVANCED_FEATURE;
    }

    // Instance getters (from the Game interface)
    @Override
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    @Override
    public String getGameType() {
        return GAME_TYPE;
    }

    @Override
    public MyHashMap<String, Integer> getShipsToPlace() {
        return SHIPS_TO_PLACE;
    }

    @Override
    public AdvancedFeature getAdvancedFeature() {
        return ADVANCED_FEATURE;
    }
}
