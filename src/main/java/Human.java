import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import helpers.validateInput.*; // Coordinate, Name

public class Human extends Player {
    public Human() {
        super();
    }

    public Human(String name) {
        super(name);
    } // for testing purposes

    public void setName() {
        this.name = Name.getName();
    }

    public void addConqueredShips(Ship ship) {
        super.addConqueredShips(ship);
    }

    public Integer makeGuess(Board opponentBoard) {
        return ThreadLocalRandom.current().nextInt(0, opponentBoard.getSize()); // todo: try to put coordinate.getcoord here so turn can go in driver
    }
    public void turn(Scanner sc, Board opponentBoard) {
//        clearConsole();
        int xCord, yCord;
        System.out.println("Turn of player " + name + "\n Here is your opponent's board");
        opponentBoard.displayBoard(true);
        xCord = Coordinate.getCoord(sc, opponentBoard.getSize(), "X");
        yCord = Coordinate.getCoord(sc, opponentBoard.getSize(), "Y");

        int hit = opponentBoard.bomb(yCord, xCord, this);
        while (hit == 2 || hit == 0) {
            if (hit == 2) {
                System.out.println("You sunk a ship, you get to go again! Here is where you hit: ");
            } else {
                System.out.println("So close, but not quite. Try entering another coordinate.");
            }
            opponentBoard.displayBoard(true);
            xCord = Coordinate.getCoord(sc, opponentBoard.getSize(), "X");
            yCord = Coordinate.getCoord(sc, opponentBoard.getSize(), "Y");

            hit = opponentBoard.bomb(yCord, xCord, this);
        }
        if (hit == 1) { //water
            System.out.println("You bombed water");
        } else {
            System.out.println("You bombed something, I wonder what it is...");
        }
    }
}
