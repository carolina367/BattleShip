import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AI extends Player {
    public AI() {
        super();
    }

    public void setName() {
        this.name = "AI";
    }

    public Integer makeGuess(Board opponentBoard) {
        return ThreadLocalRandom.current().nextInt(0, opponentBoard.getSize());
    }
    public void turn(Scanner sc, Board opponentBoard) {
        Driver.clearConsole();
        int xCord, yCord;
        xCord = makeGuess(opponentBoard);
        yCord = makeGuess(opponentBoard);;

        int hit = opponentBoard.bomb(yCord, xCord, this); // todo: see if it should be this or player
        while (hit == 2 || hit == 0) {
            xCord = makeGuess(opponentBoard);;
            yCord = makeGuess(opponentBoard);;
            hit = opponentBoard.bomb(yCord, xCord, this);
        }
    }
}
