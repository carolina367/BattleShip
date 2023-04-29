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
                gameBoard[y][x] = new Tile();
            }
        }

    }

    public int getSize() {
        return size;
    }

    public Tile getTile(int x, int y) {
        return gameBoard[y][x];
    }

    public boolean placeShip(int x, int y, Ship ship) {
        if (!overlapping(x, y, ship) && !outOfBounds(x, y, ship) && shipsToPlace.get(ship.getShipType().name()) - 1 >= 0) {
            setAllTilesInShip(x, y, ship, TileType.COVERED_SHIP);
//            System.out.println("\t ---> placing " + ship.getShipType().name().toLowerCase());
            shipsToPlace.put(ship.getShipType().name(), shipsToPlace.get(ship.getShipType().name()) - 1);
//            displayShipsToPlace( ship.getShipType().name());
            return true;
        } else {
            System.out.println("Unable to place ship: " + ship.getShipType().name().toLowerCase());
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
                    if (currTile.getShip() != null && currTile.getTileType() == TileType.COVERED_SHIP) { // show what ship type
                        System.out.print(currTile.getShip().getShipType().toString() + "  "); // TODO: tell GUI to show if the ship is normal/bombed/sunk
                    } else {
                        System.out.print(currTile.getKey() + "  ");
                    }
                } else {
                    if (currTile.getTileType() == TileType.COVERED_ROCK || currTile.getTileType() == TileType.COVERED_SHIP) { // if not bombed, show water
                        System.out.print(TileType.WATER.toString() + "  ");
                    } else if (currTile.getTileType() == TileType.UNCOVERED_ROCK || currTile.getTileType() == TileType.UNCOVERED_SHIP) {
                        System.out.print(currTile.getShip().getShipType() + "  ");
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
            if (shipName.equals(i)) {
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

<<<<<<< HEAD
    public void bomb(int x, int y, Player opponent) {
=======
    public boolean bomb(int x, int y, Player opponent) {
>>>>>>> 84288473a7c4e547e19570f2291b0c02ff3084bc
        if (y >= size || x >= size || x < 0 || y < 0) {
            System.out.println("Cannot bomb coordinate " + x + ", " + (char) ('A' + y) + ". It is out of bounds");
        } else {
            Tile currTile = gameBoard[y][x];
            if (!(currTile.getTileType() == TileType.WATER) && !(currTile.getTileType() == TileType.COVERED_SHIP) && !(currTile.getTileType() == TileType.COVERED_ROCK)) {
                System.out.println("Cannot bomb coordinate " + x + ", " + (char) ('A' + y) + ". It is already bombed");
            } else {
                if (currTile.getTileType() == TileType.WATER) {
                    currTile.setTileType(TileType.BOMBED_WATER);
                } else if (currTile.getTileType() == TileType.COVERED_SHIP) {
                    Ship currShip = currTile.getShip();
                    currShip.takeHit(); // take a hit
                    if (currShip.getIsSunk()) { // if that hit sank ship
                        setAllTilesInShip(x, y, currShip, TileType.UNCOVERED_SHIP);
                        // todo: tell player ship was bombed
                        opponent.addConqueredShips(currShip);
<<<<<<< HEAD
=======
                        return true; // sunk a ship so can go again
>>>>>>> 84288473a7c4e547e19570f2291b0c02ff3084bc
                    } else {
                        currTile.setTileType(TileType.BOMBED_SHIP);
                    }
                } else if (currTile.getTileType() == TileType.COVERED_ROCK) {
                    currTile.setTileType(TileType.BOMBED_ROCK);
                    // TODO: eventually implement the setAllTilesInObj() for the rock;
                }
            }
        }
<<<<<<< HEAD
=======
        return false;
>>>>>>> 84288473a7c4e547e19570f2291b0c02ff3084bc
    }

    public void setAllTilesInShip(int x, int y, Ship ship, TileType type) { // the coords need to be to the start of the ship
        // ensure coords are for the start of the ship
        if (type != TileType.COVERED_SHIP) {
            y = findShipStart(x, y, ship)[0];
            x = findShipStart(x, y, ship)[1];
        }

        for (int i = 0; i < ship.getLength(); i++) { //uncovering the ship on every coordinate
            int yOffset = ship.isVertical() ? i : 0;
            int xOffset = ship.isVertical() ? 0 : i;
            if (type == TileType.COVERED_SHIP) {
                gameBoard[y + yOffset][x + xOffset].setTileType(type, ship);
            } else {
                gameBoard[y + yOffset][x + xOffset].setTileType(type);
            }
        }
    }

    public int[] findShipStart(int x, int y, Ship ship) {
        int[] coords = {y, x}; // if none are found it'll return this
        for (int i = 0; i < ship.getLength() + 1; i++) { // at most, we are at the end of the ship
            int yOffset = ship.isVertical() ? i : 0;
            int xOffset = ship.isVertical() ? 0 : i;
            if (outOfBounds(x - xOffset, y - yOffset, ship) || gameBoard[y - yOffset][x - xOffset].getShip() != ship) {
                coords[0] = y - yOffset + (yOffset != 0 ? 1 : 0);
                coords[1] = x - xOffset + (xOffset != 0 ? 1 : 0);
                break;
            }
        }
        return coords;
    }

}