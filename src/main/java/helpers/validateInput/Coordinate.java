package helpers.validateInput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Coordinate {
    public static int getCoord(Scanner sc, int boardSize, String axis) {
        while (true) {
            System.out.println("Enter " + axis + " coordinate: ");
            try {
                if ("Y".equalsIgnoreCase(axis)) {
                    return getYCoordinate(sc, boardSize);
                } else {
                    return getXCoordinate(sc, boardSize);
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println(">>>> Invalid input. Please try again!");
                sc.nextLine();
            }
        }
    }

    private static int getXCoordinate(Scanner sc, int boardSize) {
        int coordinate = sc.nextInt();

        if (coordinate >= 0 && coordinate < boardSize) {
            return coordinate;
        } else {
            throw new InputMismatchException(); // thrown by nextInt()
        }
    }

    private static int getYCoordinate(Scanner sc, int boardSize) {
        char c = Character.toUpperCase(sc.next().charAt(0));
        int coordinate = c - 'A';

        if (coordinate >= 0 && coordinate < boardSize) {
            return coordinate;
        } else {
            throw new IndexOutOfBoundsException(); // thrown by charAt()
        }
    }
}
