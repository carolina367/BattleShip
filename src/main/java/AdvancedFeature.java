public enum AdvancedFeature {
    NONE("None"),
    MORE_SHIPS("More Ships"),
    BIGGER_BOARD("Bigger Board"),
    OBSTACLES("Obstacles");

    private final String displayName;

    AdvancedFeature(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
