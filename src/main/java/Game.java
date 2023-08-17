import helpers.MyHashMap;

public interface Game {
    int getBoardSize();

   String getGameType();

    MyHashMap<String, Integer> getShipsToPlace();
    AdvancedFeature getAdvancedFeature();
}
