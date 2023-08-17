import java.util.HashMap;
import java.util.Random;

public class Board {
    private final int size;
    private final Tile[][] gameBoard;
    private final MyHashMap<String, Integer> shipsToPlace;

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
        if (!overlapping(letter, num, ship) && !ship.outOfBounds(letter, num, this) && shipsToPlace.get(ship.getShipType().name()) - 1 >= 0) {
            ship.setAllTilesInShip(letter, num, this, TileState.COVERED_SHIP);
            shipsToPlace.put(ship.getShipType().name(), shipsToPlace.get(ship.getShipType().name()) - 1);
            if (output) {
                System.out.printf("\n \t ---> placing %s %s, at (x: %d, y: %c)%n", ship.getShipType(), (ship.isVertical() ? " vertically" : " horizontally"), num, (char) ('A' + letter));
                System.out.println("Ships Left: " + shipsToPlace.entrySet()); // gets the set of pairs (k, v );
            }
            return true;
        } else {
            if (output) {
                String reason = (overlapping(letter, num, ship) ? "Overlapping" : (ship.outOfBounds(letter, num, this) ? "Ship goes out of bounds" : "All ships of this type placed"));
                System.out.println("Unable to place ship: " + ship.getShipType() + " - " + reason);
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
            if (gameBoard[letter + letterOffset][num + numOffset].getState() != TileState.WATER) {
                return true; // ships are overlapping
            }
        }
        return false; // ships are NOT overlapping
    }

    public boolean isEmpty() {
        for (int num = 0; num < size - 1; num++) {
            for (int letter = 0; letter < size - 1; letter++) {
                if (gameBoard[letter][num].getState() != TileState.WATER) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayBoard(boolean opponentView) {
        String divider = "  +-";
        String h_margin = "    "; // horizontal margin

        for (int i = 0; i < size; i++) { // making the margin the size of the board
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
                Tile currTile = gameBoard[letter][num];
                if (!opponentView) { // player's personal view
                    if (currTile.getObst() != null && currTile.getState() == TileState.COVERED_SHIP) { // show what ship type
                        Ship tempShip = (Ship) currTile.getObst();
                        System.out.print(tempShip.getShipType().getAbbr() + "  "); // TODO: tell GUI to show if the ship is normal/bombed/sunk
                    } else {
                        System.out.print(currTile.getState().getAbbr() + "  ");
                    }
                } else {
                    if (currTile.getState() == TileState.COVERED_ROCK || currTile.getState() == TileState.COVERED_SHIP) { // if not bombed, show water
                        System.out.print(TileState.WATER.getAbbr() + "  ");
                    } else if (currTile.getState() == TileState.UNCOVERED_ROCK || currTile.getState() == TileState.UNCOVERED_SHIP) {
                        Ship tempShip = (Ship) currTile.getObst();
                        System.out.print(tempShip.getShipType().getAbbr() + "  ");
                    } else {
                        System.out.print(currTile.getState().getAbbr() + "  "); // anything else can show its true type
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

    public int bomb(int letter, int num, Player opponent) {
        if (outOfBounds(letter) || outOfBounds(num)) { // out of bounds
            String before = (letter < 0 ? "-" : (letter >= size ? Character.toString((char) ('A' + letter - size)) : "")); // this is ugly but it works
            System.out.println("Cannot bomb coordinate " + num + ", " + before + (char) ('A' + Math.abs(letter)) + ". It is out of bounds");
        } else {
            Tile currTile = gameBoard[letter][num];
            // already bombed
            if (!(currTile.getState() == TileState.WATER) && !(currTile.getState() == TileState.COVERED_SHIP) && !(currTile.getState() == TileState.COVERED_ROCK)) {
                System.out.println("Cannot bomb coordinate " + num + ", " + (char) ('A' + letter) + ". It is already bombed");
            } else {
                if (currTile.getState() == TileState.WATER) {
                    currTile.setState(TileState.BOMBED_WATER);
                    return 1; // hit water
                } else if (currTile.getState() == TileState.COVERED_SHIP) {
                    Ship currShip = (Ship) currTile.getObst();
                    currShip.takeHit(); // take a hit
                    if (currShip.getIsSunk()) { // if that hit sank ship
                        currShip.setAllTilesInShip(letter, num, this, TileState.UNCOVERED_SHIP);
                        opponent.addConqueredShips(currShip);
                        return 2; // sunk a ship so can go again
                    } else {
                        currTile.setState(TileState.BOMBED_SHIP);
                        return 3; // hit a ship
                    }
                } else if (currTile.getState() == TileState.COVERED_ROCK) {
                    currTile.setState(TileState.BOMBED_ROCK);
                    return 4;
                    // TODO: eventually implement the setAllTilesInObj() for the rock;
                }
            }
        }
        return 0; // didn't bomb
    }

    public boolean randomPlaceShips() {
        if (countShipsToPlace() == 0) { // 0 left to place
            for (int y = 0; y < size; y++) { // reset board
                for (int x = 0; x < size; x++) {
                    gameBoard[y][x].setState(TileState.WATER);
                }
            }
            initShipsToPlace(); // reset ships to place
        }
        Random rand = new Random();
        int count = 0;

        while (countShipsToPlace() != 0 && count <= 6) { // arbitrary count to find max iterations before fail
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

            if (countShipsToPlace() == 0) {
                System.out.println("All ships placed");
                displayBoard(false);
                return true;
            }
            count++;
        }

        for (int y = 0; y < size; y++) { // if it wasn't successful then reset and return false for fail
            for (int x = 0; x < size; x++) {
                gameBoard[y][x].setState(TileState.WATER);
            }
        }
        initShipsToPlace(); // reset ships to place
        return false;
    }

    public boolean outOfBounds(int coordinate) {
        return coordinate >= size || coordinate < 0; // coordinates are out of bounds
    }
}
