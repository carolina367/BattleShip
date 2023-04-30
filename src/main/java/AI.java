import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AI extends Player {
    private String name;
    private HashMap<String, Integer> conqueredShips;

    private int prevX;
    private int prevY;

    public AI() {
        super();
    }

    public void setAIName() {
        this.name = "AI";
    }

    public void turn(Board board) {

        int x = ThreadLocalRandom.current().nextInt(0, board.getSize());
        int y = ThreadLocalRandom.current().nextInt(0, board.getSize());

    }
}
