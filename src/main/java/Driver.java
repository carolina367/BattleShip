import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static int getValidCoordinate(Scanner sc, Board board, String str) {
//        boolean coordinateGiven = false;
        int coordinate = 0;

        while (true) {
            System.out.println("Enter " + str + " coordinate: ");
            try {
                coordinate = sc.nextInt(); // if invalid it will throw an exception
                if (coordinate < board.getSize() || coordinate >= 0) {
                    return coordinate; // if correct it'll short the loop
                }
                System.out.println("OUT OF BOUNDS: Coordinate" + coordinate + ". Enter again.");
            } catch (InputMismatchException e) {
                System.out.println("This input is not a boolean - Please try again!");
                sc.next(); // consume the invalid input and clear the input buffer
            }
        }
    }

    public static boolean getValidBooleanInput(Scanner sc) {
        boolean input;

        while (true) {
            System.out.println("Is the ship vertical? Enter 'true' or 'false': ");
            try {
                input = sc.nextBoolean(); // if invalid it will throw an exception
                return input;
            } catch (InputMismatchException e) {
                System.out.println("This input is not a boolean - Please try again!");
                sc.next(); // consume the invalid input and clear the input buffer
            }
        }
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

            // Standardize string - rm non-alphabetic chars
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

