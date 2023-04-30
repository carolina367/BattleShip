import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to battleship");
        Scanner sc = new Scanner(System.in);

        ///////// INITIALIZE /////////
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

        while (((Human) p1).getHumanName().equalsIgnoreCase(((Human) p2).getHumanName())) {
            System.out.println("That is the same name as p1. Pick a new name please");
            p2.setName();
        }

        ///////// Set up Board /////////
        System.out.println(String.format("Player %s Would you like to set up your board manually or randomly generated? Enter 'true' for manual, 'false' for random", p1.getName()));
        boolean manualSetUp = getValidBooleanInput(sc);

        // player 1
        if (manualSetUp) {
            manualPlaceAllShips(board1, sc);
        } else {
            // todo: put in the random place function
        }

        // player 2
        if (p2 instanceof Human) {
            System.out.println(String.format("Player %s Would you like to set up your board manually or randomly generated? Enter 'true' for manual, 'false' for random", p2.getName()));
            manualSetUp = getValidBooleanInput(sc);

            if (manualSetUp) {
                manualPlaceAllShips(board2, sc);
            } else {
                // todo: put in the random place function
            }
        } else if (p2 instanceof AI) {
            // todo: put in the random place function
        }

        sc.close();
    }

    public static String play(Scanner sc, Player p1, Player p2, Board gameBoard) { // haven't fully implemented yet
        final int totShips = gameBoard.countShipsToPlace();
        Integer counter = 0;
        while (p1.countConqueredShips() != totShips && p2.countConqueredShips() != totShips) {
            if (counter % 2 == 0) {
//                turn(sc, p1, gameBoard);
            } else {
//                turn(sc, p2, gameBoard);
            }
            gameBoard.displayBoard(true);
            counter++;
        }

        if (p1.countConqueredShips() == p2.countConqueredShips()) {
            System.out.println("There was a tie. Would you like to play again? Enter 'true' for yes, 'false' for no");
            boolean next = getValidBooleanInput(sc);
            if (next) {
                play(sc, p1, p2, gameBoard);
            } else {
                return ("\t Thank you for playing\n");
            }
        } else if (p1.countConqueredShips() > p2.countConqueredShips()) {
            return p1.getName();
        }
        return p2.getName();
    }


    public static int getValidCoordinate(Scanner sc, Board board, String str) {
        int coordinate = -1;
        while (true) {
            System.out.println("Enter " + str + " coordinate: ");
            try {
                String errMessage = "OUT OF BOUNDS";
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

                if (coordinate < board.getSize() && coordinate >= 0) {
                    return coordinate; // if correct it'll short the loop
                }
                System.out.println(errMessage + ". Enter again.");
            } catch (InputMismatchException e) {
                System.out.println("This input is not of the correct type - Please try again!");
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
                System.out.println("This input is not a boolean - Please try again!");
            } catch (InputMismatchException e) {
                System.out.println("This input is not a boolean - Please try again!");
                sc.nextLine();
            }
        }
    }

    public static void manualPlaceAllShips(Board board, Scanner sc) {
        int xCord, yCord;
        boolean isVertical;
        String shipInput;
        Ship newShip = null;
        outer:
        while (board.countShipsToPlace() > 0) {
            System.out.println(String.format("You have %d ships left to place. Remember, the board is from x: 0-%d, y: A-%c", board.countShipsToPlace(), (board.getSize() - 1), (char) ('A' + board.getSize() - 1)));
            xCord = getValidCoordinate(sc, board, "X (as num)");
            yCord = getValidCoordinate(sc, board, "Y (as char)");

            System.out.println("Is the ship vertical? Enter 'true' or 'false': ");
            isVertical = getValidBooleanInput(sc);

            boolean terminate = false;
            while (terminate == false) {

                System.out.println("Valid ships: " + board.getShipsToPlace().entrySet());
                System.out.println("Enter Ship Type: ");
                shipInput = sc.next().toUpperCase();

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
                    System.out.println("Invalid ship type. Would you like to enter another ship name or enter new coordinates? Enter 'true' for another ship name, 'false' for new coordinates.");
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
}
