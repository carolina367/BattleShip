import java.util.Map;
import java.util.Set;
import java.util.EnumMap;
import java.util.EnumSet;

public class TileStateTransitions {

    private static final Map<TileState, Set<TileState>> allowedTransitions = new EnumMap<>(TileState.class);

    static {
        allowedTransitions.put(TileState.WATER, EnumSet.of(TileState.BOMBED_WATER, TileState.COVERED_SHIP, TileState.COVERED_ROCK));
        allowedTransitions.put(TileState.BOMBED_WATER, EnumSet.noneOf(TileState.class));

        allowedTransitions.put(TileState.COVERED_ROCK, EnumSet.of(TileState.BOMBED_ROCK, TileState.UNCOVERED_ROCK));
        allowedTransitions.put(TileState.BOMBED_ROCK, EnumSet.of(TileState.UNCOVERED_SHIP));
        allowedTransitions.put(TileState.UNCOVERED_ROCK, EnumSet.noneOf(TileState.class));

        allowedTransitions.put(TileState.COVERED_SHIP, EnumSet.of(TileState.BOMBED_SHIP, TileState.UNCOVERED_SHIP));
        allowedTransitions.put(TileState.BOMBED_SHIP, EnumSet.of(TileState.UNCOVERED_SHIP));
        allowedTransitions.put(TileState.UNCOVERED_SHIP, EnumSet.noneOf(TileState.class));
    }

    public static boolean isAllowed(TileState from, TileState to) {
        return allowedTransitions.get(from).contains(to);
    }
}
