import java.util.HashMap;
import java.lang.IndexOutOfBoundsException;

public class Board {
    private final int size;
    private final Tile[][] gameBoard;
    private final HashMap<String, Integer> shipsToPlace;

    public Board(int size) {
        shipsToPlace = new HashMap<>();
        shipsToPlace.put("CARRIER", 1);
        shipsToPlace.put("BATTLESHIP", 1);
        shipsToPlace.put("CRUISER", 2);
        shipsToPlace.put("DESTROYER", 1);

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

    public boolean placeShip(int x, int y, Ship ship) {
        if (!overlapping(x, y, ship) && !outOfBounds(x, y, ship) && shipsToPlace.get(ship.getShipType().name()) - 1 >= 0) {
            for (int i = 0; i < ship.getLength(); i++) { //placing the ship on every coordinate
                if (ship.isVertical()) {
                    gameBoard[y + i][x].setTileType(TileType.COVERED_SHIP, ship);
                } else {
                    gameBoard[y][x + i].setTileType(TileType.COVERED_SHIP, ship);
                }
            }
            System.out.println("\t ---> placing " + ship.getShipType().name().toLowerCase());
            shipsToPlace.put(ship.getShipType().name(), shipsToPlace.get(ship.getShipType().name()) - 1);
            displayShipsToPlace( ship.getShipType().name());
            return true;
        } else {
            System.out.println("Unable to place ship: "+ ship.getShipType().name().toLowerCase());
            return false;
        }
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
            if (ship.isVertical() && gameBoard[y][x + i].getTileType() != TileType.WATER) {
                return true; // ships are overlapping
            } else if (gameBoard[y + i][x].getTileType() != TileType.WATER) {
                return true; // ships are overlapping
            }
        }
        return false; // ships are NOT overlapping
    }

    public boolean isEmpty() {
        for (int x = 0; x < size - 1; x++) {
            for (int y = 0; y < size - 1; y++) {
                if (gameBoard[y][x].getTileType() != TileType.WATER) {
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

        System.out.println(h_margin + "\n" + divider + "X: num, Y: char");

        int counter = 0;

        for (int x = 0; x < size; x++) {
            System.out.print((char) ('A' + counter) + " | "); // vertical margin
            for (int y = 0; y < size; y++) {
                Tile currTile = gameBoard[x][y];
                if (!opponentView) { // player's personal view
                    if (currTile.getShip() != null) { // show what ship type
                        System.out.print(currTile.getShip().getShipType().toString() + "  "); // todo: tell GUI to show if the ship is normal/bombed/sunk
                    } else {
                        System.out.print(currTile.getKey() + "  ");
                    }
                } else {
                    if (currTile.getTileType() == TileType.COVERED_ROCK || currTile.getTileType() == TileType.COVERED_SHIP) { // if not bombed, show water
                        System.out.print(TileType.WATER.toString() + "  ");
                    } else if (currTile.getTileType() == TileType.BOMBED_ROCK || currTile.getTileType() == TileType.BOMBED_SHIP) { // if not uncovered, show bombed
                        System.out.print(TileType.BOMBED_WATER.toString() + "  ");
                    } else {
                        System.out.print(currTile.getKey() + "  "); // anything else can show its true type
                    }
                }
            }
            System.out.println("|");
            counter++;
        }

        System.out.println(divider + "\n" + h_margin);

    }

    public void displayShipsToPlace(String shipName) {
        int sumOfShips = 0;
        String toPrint = "";
        for (String i : shipsToPlace.keySet()) {
            if(shipName == i) {
                toPrint = " <--- decremented";
            }
            System.out.println("\t" + i.toLowerCase() + ": " + shipsToPlace.get(i) + " left to place" + toPrint);

            sumOfShips += shipsToPlace.get(i);
            toPrint = "";
        }

        if (sumOfShips == 0) {
            System.out.println("No ships left to place!");
        }
    }

    public int countShipsToPlace() {
        int sumOfShips = 0;
        for (String i : shipsToPlace.keySet()) {
            sumOfShips += shipsToPlace.get(i);
        }
        return sumOfShips;
    }

}