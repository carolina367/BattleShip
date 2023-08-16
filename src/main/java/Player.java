import java.util.Scanner;

public abstract class Player {
    protected String name;
    protected final ConqueredShips conqueredShips;

    public Player() {
        this.name = "";
        this.conqueredShips = new ConqueredShips();
    }

    public Player(String name) {
        this.name = name;
        this.conqueredShips = new ConqueredShips();
    }

    public void configureName(Player opponent){} // todo: only needed for human but reference in driver

    public String getName(){
        return name;
    };
    public abstract void setName();

    public void displayConqueredShips() {
        conqueredShips.displayCS();
    }

    public int countConqueredShips() {
        return conqueredShips.countCS();
    }

    public void addConqueredShips(Ship ship) {
        conqueredShips.addCS(ship);
    }

    public abstract void turn(Scanner sc, Board opponentBoard);
}
