import java.util.HashMap;

public class AI extends Player {
    private String name;
    private HashMap<String, Integer> conqueredShips;

    public AI() {
        super();
    }

    public void setName() {
        this.name = "AI";
    }

    public String getName() {
        return this.name;
    }

//    public void turn(Board board) {
//
//        int x = ThreadLocalRandom.current().nextInt(0, board.getSize());
//        int y = ThreadLocalRandom.current().nextInt(0, board.getSize());
//
//    }
}
