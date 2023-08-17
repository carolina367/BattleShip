public class Tile {
    private TileState state;
    private Obstacle obstacle;

    public Tile() {
        this.state = TileState.WATER;
        this.obstacle = null;
    }

    public Obstacle getObst() {
        return obstacle;
    }

    public TileState getState() {
        return state;
    }


    // This setState doesn't allow transitions to a 'covered' state
    public void setState(TileState newState) {
        if (isNewStateCovered(newState)) {
            throw new IllegalStateException("Cannot transition to a 'covered' state with this method.");
        }
        if (TileStateTransitions.isAllowed(this.state, newState)) {
            this.state = newState;
        } else {
            throw new IllegalStateException("Cannot transition from " + this.state + " to " + newState);
        }
    }

    // This setState is only for transitions to a 'covered' state and requires an additional obstacle parameter
    public void setState(TileState newState, Obstacle newObstacle) {
        if (isNewStateCovered(newState) && TileStateTransitions.isAllowed(this.state, newState)) {
            this.state = newState;
            this.obstacle = newObstacle;
        } else {
            throw new IllegalStateException("Cannot transition from " + this.state + " to " + newState);
        }
    }

    private boolean isNewStateCovered(TileState state) {
        return state.name().startsWith("COVERED");
    }
}