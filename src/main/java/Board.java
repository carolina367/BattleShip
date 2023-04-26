import java.util.HashMap;
import java.lang.IndexOutOfBoundsException;

public class Board {
    private final int size;
    private final Tile[][] gameBoard;
    private final HashMap<String, Integer> shipsLeft;

    public Board(int size) {
        shipsLeft = new HashMap<>();
        shipsLeft.put("CARRIER", 1);
        shipsLeft.put("BATTLESHIP", 1);
        shipsLeft.put("CRUISER", 2);
        shipsLeft.put("DESTROYER", 1);

        this.size = size;
        this.gameBoard = new Tile[size][size];
        // nested loop to add all indexes as a new tile
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                gameBoard[x][y] = new Tile();
            }
        }

    }

    public int getSize() {
        return size;
    }

    public Tile getTile(int x, int y) {
        return gameBoard[x][y];
    }

    public void placeShip(int x, int y, Ship ship) {
//        try {
        if (!overlapping(x, y, ship) && !outOfBounds(x, y, ship)) {
            for (int i = 0; i < ship.getLength(); i++) { //placing the ship on every coordinate
                if (ship.isVertical()) {
                    gameBoard[x][y + i].setTileType(TileType.COVERED_SHIP, ship);
                } else {
                    gameBoard[x + i][y].setTileType(TileType.COVERED_SHIP, ship);
                }
            }
            shipsLeft.put(ship.getShipType().name(), shipsLeft.get(ship.getShipType().name()) - 1);

            int sumOfShips = 0;
            for (String i : shipsLeft.keySet()) {
                System.out.println("ShipType: " + i + " | Ships left to be placed: " + shipsLeft.get(i));
                sumOfShips += shipsLeft.get(i);
            }

            if (sumOfShips == 0) {
                System.out.println("No ships left!");
            }
        }
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
    }

    public boolean outOfBounds(int x, int y, Ship ship) {
        for (int i = 0; i < ship.getLength(); i++) { // checking that the ship isn't overlapping any coordinate
            if (y + i >= size || x + i >= size || x < 0 || y < 0) {
                return true; // coordinates are out of bounds
            }
        }
        return false; // coordinates are within bounds
    }

    public boolean overlapping(int x, int y, Ship ship) {
        for (int i = 0; i < ship.getLength(); i++) { // checking that the ship isn't overlapping any coordinate
            if (y + i >= size || x + i >= size) {
                break;
            }
            if (ship.isVertical() && gameBoard[x][y + i].getTileType() != TileType.WATER) {
                return true; // ships are overlapping
            } else if (gameBoard[x + i][y].getTileType() != TileType.WATER) {
                return true; // ships are overlapping
            }
        }
        return false; // ships are NOT overlapping
    }

    public boolean isEmpty() {
        for (int x = 0; x < size - 1; x++) {
            for (int y = 0; y < size - 1; y++) {
                if (gameBoard[x][y].getTileType() != TileType.WATER) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayBoard(boolean opponentView) {
        String divider = "  +-";
        String h_margin = "    "; // horizontal margin

        for (int i = 0; i < size; i++) { //making the margin the size of the board
            if (i < 10) {
                h_margin += " ";
            }
            divider += "----";
            h_margin += i + "  ";

            if (i == size - 1) {
                divider += "+ ";
            }
        }

        System.out.println(h_margin + "\n" + divider);

        int counter = 0;

        for (int x = 0; x < size; x++) {
            System.out.print(counter + " | "); // vertical margin
            for (int y = 0; y < size; y++) {
                if (!opponentView) { // player's personal view
                    if (gameBoard[y][x].getShip() != null) { // show what ship type
                        System.out.print(gameBoard[y][x].getShip().getShipType().toString() + "  "); // todo: tell GUI to show if the ship is normal/bombed/sunk
                    } else {
                        System.out.print(gameBoard[y][x].getKey() + "  ");
                    }
                } else {
                    TileType currTile = gameBoard[y][x].getTileType();
                    if (currTile == TileType.COVERED_ROCK || currTile == TileType.COVERED_SHIP) { // if not bombed, show water
                        System.out.print(TileType.WATER.toString() + "  ");
                    } else if (currTile == TileType.BOMBED_ROCK || currTile == TileType.BOMBED_SHIP) { // if not uncovered, show bombed
                        System.out.print(TileType.BOMBED_WATER.toString() + "  ");
                    } else {
                        System.out.print(gameBoard[y][x].getKey() + "  "); // anything else can show its true type
                    }
                }
            }
            System.out.println("|");
            counter++;
        }

        System.out.println(divider + "\n" + h_margin);

    }

    public int shipsLeft() {
        int sumOfShips = 0;
        for (String i : shipsLeft.keySet()) {
            System.out.println("Ship Type: " + i + " | Ships left to be placed: " + shipsLeft.get(i));
            sumOfShips += shipsLeft.get(i);
        }

        if (sumOfShips == 0) {
            System.out.println("No ships left!");
        }
        return sumOfShips;
    }
}