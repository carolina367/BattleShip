import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static int getValidCoordinate(Scanner sc, Board board, String str) {
        boolean coordinateGiven = false;
        int coordinate = 0;

        while (!coordinateGiven) {
            System.out.println("Enter " + str + " coordinate: ");
            try {
                coordinate = sc.nextInt();
                if ((coordinate >= board.getSize()) || (coordinate < 0)) {
//                    throw new IndexOutOfBoundsException("OUT OF BOUNDS: Coordinate " + coordinate);
                    System.out.println("Out of bounds! Enter again.");
                    continue;
                }
                coordinateGiven = true;
            } catch (InputMismatchException e) {
                System.out.println("This input is not an integer - Please try again!");
                // consume the invalid input and clear the input buffer
                sc.next();
            }
//            catch (IndexOutOfBoundsException e) {
//                System.out.println("Error: " + e);
//            }
        }
        return coordinate;
    }

    public static boolean getValidBooleanInput(Scanner sc) {
        boolean validInputGiven = false;
        boolean input = false;

        while (!validInputGiven) {
            System.out.println("Is the ship vertical? Enter 'true' or 'false': ");
            try {
                input = sc.nextBoolean();
                validInputGiven = true;
            } catch (InputMismatchException e) {
                System.out.println("This input is not a boolean - Please try again!");
                // consume the invalid input and clear the input buffer
                sc.next();
            }
        }
        return input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board gameBoard1 = new Board(10);
        Board gameBoard2 = new Board(10);
        int xCord = getValidCoordinate(sc, gameBoard1, "X");
        int yCord = getValidCoordinate(sc, gameBoard1, "Y");
        boolean isVertical = getValidBooleanInput(sc);

        // consume any leftover newline character
        sc.nextLine();

        String shipInput;
        Ship newShip = null;
        while (true) {
            System.out.println("Enter Ship Type: ");
            shipInput = sc.nextLine();

            // Standardize string
            shipInput = shipInput.replaceAll("[^A-Za-z]", "");

            // For loop through ship types and compare
            for (ShipType type : ShipType.values()) {
                if (type.name().equalsIgnoreCase(shipInput)) {
                    // Create ship of this type:
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
                System.out.println("Invalid ship type. Please try again.");
                continue;
            }

            System.out.println("X Coordinate: " + xCord + "\nY Coordinate: " + yCord + "\nisVertical: " + isVertical + "\nShip Type: " + newShip.getShipType());

            // TODO: Make this to handle the gameBoard number based on the player turn
            // Validate the x and y type exception again
            while (gameBoard1.outOfBounds(xCord, yCord, newShip)) {
                System.out.println("Invalid coordinates. Please enter valid coordinates and isVertical.");
                xCord = getValidCoordinate(sc, gameBoard1, "X");
                yCord = getValidCoordinate(sc, gameBoard1, "Y");
                isVertical = getValidBooleanInput(sc);
            }

            // TODO: add code to place the ship on the game board
            // ...

            break; // exit the loop once a valid ship and coordinates are entered
        }
    }
}

