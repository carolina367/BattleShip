import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to battleship");
        Scanner sc = new Scanner(System.in);

        ///////// INITIALIZE  BOARD AND PLAYERS /////////
        // player 1
        Board board1 = new Board(10);
        Player p1 = new Human();
        p1.setName();

        System.out.println("Would you like to play against a human or AI opponent? Enter 'true' for human, 'false' for AI");
        boolean opponent = getValidBooleanInput(sc);

        // player 2
        Board board2 = new Board(10);
        Player p2 = opponent ? new Human() : new AI();
        p2.setName();

        if (p2 instanceof Human) {
            while (p1.getName().equalsIgnoreCase(p2.getName())) {
                System.out.println("That is the same name as p1. Pick a new name please");
                p2.setName();
            }
        } else if (p2 instanceof AI) {
            System.out.println("Your opponent is AI, their name is " + p2.getName());
        }

        ///////// SET UP BOARD /////////
        // player 1
        generateBoard(sc, p1, board1);

        // Player 2
        if (p2 instanceof Human) {
            generateBoard(sc, p2, board2);
        } else if (p2 instanceof AI) {
            board2.randomPlaceShips();
        }

        String winner = play(sc, p1, p2, board1, board2);

        System.out.println("The winner is " + winner);
        sc.close();
    }

    private static void generateBoard(Scanner sc, Player player, Board board) {
        System.out.println(String.format("\nPlayer %s, Would you like to set up your board manually or randomly generated? Enter 'true' for manual, 'false' for random", player.getName()));
        boolean manualSetUp = getValidBooleanInput(sc);

        if (manualSetUp) {
            manualPlaceAllShips(board, sc);
        } else {
            board.randomPlaceShips();
//            board.displayBoard(false); // todo: uncomment after debugging
            System.out.println("Here is your randomly generated board. Enter 'true' if you are satisfied, 'false' to regenerate");
            boolean answer = getValidBooleanInput(sc);
            while (answer == false) {
                board.randomPlaceShips();
//                board.displayBoard(false); // todo: uncomment after debugging
                System.out.println("Here is your randomly generated board. Enter 'true' if you are satisfied, 'false' to regenerate");
                answer = getValidBooleanInput(sc);
            }
        }
    }

    public static String play(Scanner sc, Player p1, Player p2, Board gameBoard1, Board gameBoard2) { // haven't fully implemented yet
        Integer counter = 0;
        while (p1.countConqueredShips() != 5 && p2.countConqueredShips() != 5) { // todo put as variable
            if (counter % 2 == 0) {
                turn(sc, p1, gameBoard2);
//                gameBoard1.displayBoard(true);
            } else {
                turn(sc, p2, gameBoard1);
//                gameBoard2.displayBoard(true);
            }
            counter++;
        }

        if (p1.countConqueredShips() == p2.countConqueredShips()) {
            System.out.println("There was a tie. Would you like to play again? Enter 'true' for yes, 'false' for no");
            boolean next = getValidBooleanInput(sc);
            if (next) {
                play(sc, p1, p2, gameBoard1, gameBoard2);
            } else {
                return ("\t Thank you for playing\n");
            }
        } else if (p1.countConqueredShips() > p2.countConqueredShips()) {
            return p1.getName();
        }
        return p2.getName();
    }

    public static void turn(Scanner sc, Player player, Board opponentBoard) {
        clearConsole();
        int xCord, yCord;
        if (player instanceof Human) {
            System.out.println("Turn of player " + player.getName() + "\n Here is your opponent's board");
            opponentBoard.displayBoard(true);
            xCord = getValidCoordinate(sc, opponentBoard.getSize(), "X (as num)");
            yCord = getValidCoordinate(sc, opponentBoard.getSize(), "Y (as char)");
        } else {
            xCord = ThreadLocalRandom.current().nextInt(0, opponentBoard.getSize());
            yCord = ThreadLocalRandom.current().nextInt(0, opponentBoard.getSize());
        }

        int hit = opponentBoard.bomb(yCord, xCord, player);
        while (hit == 2 || hit == 0) { //TODO: this has a bug
            if (player instanceof Human) {
                if (hit == 2) {
                    System.out.println("You sunk a ship, you get to go again! Here is where you hit: ");
                } else {
                    System.out.println("So close, but not quite. Try entering another coordinate.");
                }
                opponentBoard.displayBoard(true);
                xCord = getValidCoordinate(sc, opponentBoard.getSize(), "X (as num)");
                yCord = getValidCoordinate(sc, opponentBoard.getSize(), "Y (as char)");
            } else {
                xCord = ThreadLocalRandom.current().nextInt(0, opponentBoard.getSize());
                yCord = ThreadLocalRandom.current().nextInt(0, opponentBoard.getSize());
            }
            hit = opponentBoard.bomb(yCord, xCord, player);
        }
        if (player instanceof Human) {
            if (hit == 1) { //water
                System.out.println("You bombed water");
            } else {
                System.out.println("You bombed something, I wonder what it is...");
            }
        }
    }

    public static int getValidCoordinate(Scanner sc, int boardSize, String str) {
        int coordinate = -1;
        while (true) {
            System.out.println("Enter " + str + " coordinate: ");
            try {
                String errMessage = ">>>> OUT OF BOUNDS";
                if (str.contains("Y")) {
                    char c = sc.next().charAt(0);
                    if (Character.isLetter(c)) {
                        coordinate = Character.toUpperCase(c) - 'A'; // turn char to upper and then to int
                    } else {
                        errMessage += ". Input is not a letter";
                    }
                } else {
                    coordinate = sc.nextInt(); // if invalid it will throw an exception
                }

                if (coordinate < boardSize && coordinate >= 0) {
                    return coordinate; // if correct it'll short the loop
                }
                System.out.println(errMessage + ". Enter again.");
            } catch (InputMismatchException e) {
                System.out.println(">>>> This input is not of the correct type - Please try again!");
                sc.nextLine();
            }
        }
    }

    public static boolean getValidBooleanInput(Scanner sc) {
        char input;

        while (true) {
            try {
                input = Character.toUpperCase(sc.next().charAt(0)); // if invalid it will throw an exception
                if (input == 'T') {
                    return true;
                } else if (input == 'F') {
                    return false;
                }
                System.out.println(">>>> This input is not a boolean - Please try again!");
            } catch (InputMismatchException e) {
                System.out.println(">>>> This input is not a boolean - Please try again!");
                sc.nextLine();
            }
        }
    }

    public static void manualPlaceAllShips(Board board, Scanner sc) {
        Ship newShip = null;
        outer:
        while (board.countShipsToPlace() > 0) {
            System.out.println(String.format("You have %d ships left to place. Remember, the board is from x: 0-%d, y: A-%c", board.countShipsToPlace(), (board.getSize() - 1), (char) ('A' + board.getSize() - 1)));
            int xCord = getValidCoordinate(sc, board.getSize(), "X (as num)");
            int yCord = getValidCoordinate(sc, board.getSize(), "Y (as char)");

            System.out.println("Is the ship vertical? Enter 'true' or 'false': ");
            boolean isVertical = getValidBooleanInput(sc);

            boolean terminate = false;
            while (terminate == false) {

                System.out.println("Valid ships: " + board.getShipsToPlace().entrySet());
                System.out.println("Enter Ship Type: ");
                String shipInput = sc.next().toUpperCase();
                while (!shipInput.matches("[a-zA-Z]+")) { // only characters
                    shipInput = sc.next().toUpperCase();
                }

                for (ShipType type : ShipType.values()) {
                    if (type.name().equalsIgnoreCase(shipInput)) {

                        newShip = switch (type) {
                            case CARRIER -> newShip = new Ship(isVertical, ShipType.CARRIER);
                            case BATTLESHIP -> newShip = new Ship(isVertical, ShipType.BATTLESHIP);
                            case CRUISER -> newShip = new Ship(isVertical, ShipType.CRUISER);
                            case DESTROYER -> newShip = new Ship(isVertical, ShipType.DESTROYER);
                        };
                        break;
                    }
                }

                if (newShip == null) {
                    System.out.println(">>>> Invalid ship type. \n Would you like to enter another ship name or enter new coordinates? Enter 'true' for another ship name, 'false' for new coordinates.");
                    boolean anotherShip = getValidBooleanInput(sc);
                    if (!anotherShip) {
                        continue outer; // restart outer while loop
                    } else {
                        continue; // restart inner while loop
                    }
                } else {
                    board.placeShip(yCord, xCord, newShip, true); // the true will handle outputting any relevant info in both success and fail state
                    board.displayBoard(false);
                    terminate = true;
                }
            }
        }
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
