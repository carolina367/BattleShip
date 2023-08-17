public class GameFactory {

    public enum GameTypes {
        STANDARD;
        // todo: ... other game types ...
    }

    public static Game createGame(GameTypes type) {
        switch (type) {
            case STANDARD:
                return new StandardGame();
            // todo: Add more cases as I add more game types.
            default:
                throw new IllegalArgumentException("Unsupported game type: " + type);
        }
    }
}
