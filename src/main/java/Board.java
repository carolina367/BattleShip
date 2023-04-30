import java.util.HashMap;
import java.util.Random;

public class Board {
    private final int size;
    private Tile[][] gameBoard;
    private HashMap<String, Integer> shipsToPlace;

    public Board(int size) {
        shipsToPlace = new MyHashMap<>();
        initShipsToPlace();

        this.size = size;
        this.gameBoard = new Tile[size][size];
        // nested loop to add all indexes as a new tile
        for (int letter = 0; letter < size; letter++) {
            for (int num = 0; num < size; num++) {
                gameBoard[letter][num] = new Tile();
            }
        }

    }

    public void initShipsToPlace() {
        shipsToPlace.put("CARRIER", 1);
        shipsToPlace.put("BATTLESHIP", 1);
        shipsToPlace.put("CRUISER", 2);
        shipsToPlace.put("DESTROYER", 1);
    }

    public int getSize() {
        return size;
    }

    public Tile getTile(int letter, int num) {
        return gameBoard[letter][num];
    }

    public boolean placeShip(int letter, int num, Ship ship, boolean output) {
        if (!overlapping(letter, num, ship) && !ship.outOfBounds(letter, num, size) && shipsToPlace.get(ship.getShipType().name()) - 1 >= 0) {
            ship.setAllTilesInShip(letter, num, this, TileType.COVERED_SHIP);
            shipsToPlace.put(ship.getShipType().name(), shipsToPlace.get(ship.getShipType().name()) - 1);
            if (output) {
                System.out.println(String.format("\n \t ---> placing %s %s, at (x: %d, y: %c)", ship.getShipType().name().toLowerCase(), (ship.isVertical() ? " vertically" : " horizontally"), num, (char) ('A' + letter)));
                System.out.println("Ships Left: " + shipsToPlace.entrySet()); // gets the set of pairs (k, v );
            }
            return true;
        } else {
            if (output) {
                String reason = (overlapping(letter, num, ship) ? "Overlapping" : (ship.outOfBounds(letter, num, size) ? "Ship goes out of bounds" : "All ships of this type placed"));
                System.out.println("Unable to place ship: " + ship.getShipType().name().toLowerCase() + " - " + reason);
            }
            return false;
        }
    }

    public boolean overlapping(int letter, int num, Ship ship) {
        for (int i = 0; i < ship.getLength(); i++) { // checking that the ship isn't overlapping any coordinate
            if (letter + i >= size || num + i >= size) {
                break;
            }
            int letterOffset = ship.isVertical() ? i : 0;
            int numOffset = ship.isVertical() ? 0 : i;
            if (gameBoard[letter + letterOffset][num + numOffset].getTileType() != TileType.WATER) {
                return true; // ships are overlapping
            }
        }
        return false; // ships are NOT overlapping
    }

    public boolean isEmpty() {
        for (int num = 0; num < size - 1; num++) {
            for (int letter = 0; letter < size - 1; letter++) {
                if (gameBoard[letter][num].getTileType() != TileType.WATER) {
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

        for (int letter = 0; letter < size; letter++) {
            System.out.print((char) ('A' + counter) + " | "); // vertical margin
            for (int num = 0; num < size; num++) {
                Tile currTile = gameBoard[letter][num]; // how i had it Tile currTile = gameBoard[num][letter]; //todo look into thos
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

    public HashMap<String, Integer> getShipsToPlace() {
        return shipsToPlace;
    }


    public int countShipsToPlace() {
        int sumOfShips = 0;
        for (String i : shipsToPlace.keySet()) {
            sumOfShips += shipsToPlace.get(i);
        }
        return sumOfShips;
    }

    public boolean bomb(int letter, int num, Player opponent) {
        if (letter >= size || num >= size || num < 0 || letter < 0) {
            String before = (letter < 0 ? "-" : (letter >= size ? Character.toString((char) ('A' + letter - size)) : "")); // this is ugly but it works
            System.out.println("Cannot bomb coordinate " + num + ", " + before + (char) ('A' + Math.abs(letter)) + ". It is out of bounds");
        } else {
            Tile currTile = gameBoard[letter][num];
            if (!(currTile.getTileType() == TileType.WATER) && !(currTile.getTileType() == TileType.COVERED_SHIP) && !(currTile.getTileType() == TileType.COVERED_ROCK)) {
                System.out.println("Cannot bomb coordinate " + num + ", " + (char) ('A' + letter) + ". It is already bombed");
            } else {
                if (currTile.getTileType() == TileType.WATER) {
                    currTile.setTileType(TileType.BOMBED_WATER);
                } else if (currTile.getTileType() == TileType.COVERED_SHIP) {
                    Ship currShip = currTile.getShip();
                    currShip.takeHit(); // take a hit
                    if (currShip.getIsSunk()) { // if that hit sank ship
                        currShip.setAllTilesInShip(letter, num, this, TileType.UNCOVERED_SHIP);
                        // todo: tell player ship was bombed
                        opponent.addConqueredShips(currShip);
                        return true; // sunk a ship so can go again
                    } else {
                        currTile.setTileType(TileType.BOMBED_SHIP);
                    }
                } else if (currTile.getTileType() == TileType.COVERED_ROCK) {
                    currTile.setTileType(TileType.BOMBED_ROCK);
                    // TODO: eventually implement the setAllTilesInObj() for the rock;
                }
            }
        }
        return false; // if it couldn't bomb OR if it did bomb but only once
    }

    public void randomPlaceShips() {
        if (countShipsToPlace() == 0) {
            for (int y = 0; y < size; y++) { // reset board
                for (int x = 0; x < size; x++) {
                    gameBoard[y][x].setTileType(TileType.WATER);
                }
            }
            initShipsToPlace(); // reset ships to place
        }
        Random rand = new Random();
        while (this.countShipsToPlace() != 0) {
            boolean isVertical = rand.nextBoolean();
            int num = rand.nextInt(10);
            int letter = rand.nextInt(10);
            for (ShipType type : ShipType.values()) {
                Ship testShip = new Ship(isVertical, type);
                while (!placeShip(letter, num, testShip, false)) {
                    num = rand.nextInt(10);
                    letter = rand.nextInt(10);
                    testShip.setVertical(rand.nextBoolean());
                }
            }
            // for the last cruiser
            Ship testShip = new Ship(isVertical, ShipType.CRUISER);
            while (!placeShip(letter, num, testShip, false)) {
                num = rand.nextInt(10);
                letter = rand.nextInt(10);
                testShip.setVertical(rand.nextBoolean());
            }

            if (countShipsToPlace() != 0) {
                // reset board
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        gameBoard[y][x].setTileType(TileType.WATER);
                    }
                }
            }
        }
        System.out.println("All ships placed");
        displayBoard(false);
    }


}
