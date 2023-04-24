import java.util.HashMap;

public class Board {
    private int size;
    private Tile[][] gameBoard;
    private HashMap<String, Integer> shipsLeft;

    public Board(int size) {
        shipsLeft = new HashMap<String, Integer>();
        shipsLeft.put("CARRIER", 1);
        shipsLeft.put("BATTLESHIP", 1);
        shipsLeft.put("CRUISER", 2);
        shipsLeft.put("DESTROYER", 1);

        this.size = size;
        this.gameBoard = new Tile[size][size];
        // nested loop to add all indexes as a new tile
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = new Tile();
                gameBoard[x][y] = tile;
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
        try{

            overlapping(x,y,ship);
            objOutOfBounds(x,y,ship);
            for (int i = 0; i < ship.getLength(); i++) { //placing the ship on every coordinate
                if (ship.isVertical()) {
                    gameBoard[x][y + i].setTileType(TileTypes.COVERED_SHIP);
                } else {
                    gameBoard[x + i][y].setTileType(TileTypes.COVERED_SHIP);
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
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }
    public boolean objOutOfBounds(int x, int y, Ship ship) {
        try{
            for (int i = 0; i < ship.getLength(); i++) { // checking that the ship isn't overlapping any coordinate
                if (y + i >= size || x + i >= size) {
                    System.out.println("OUT OF BOUNDS; FAILED to place ship on coordinates: " + x + ", " + y);
                    return false;
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
        return true;
    }
    public boolean overlapping(int x, int y, Ship ship) {
        try{
            for (int i = 0; i < ship.getLength(); i++) { // checking that the ship isn't overlapping any coordinate
                if (ship.isVertical()) {
                    if (gameBoard[x][y + i].getTileType() != TileTypes.WATER) {
                        System.out.println("TILE ALREADY OCCUPIED; FAILED to place ship on coordinates: " + x + ", " + y);
                        return true;
                    }
                } else {
                    if (gameBoard[x + i][y].getTileType() != TileTypes.WATER) {
                        System.out.println("TILE ALREADY OCCUPIED; FAILED to place ship on coordinates: " + x + ", " + y);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public boolean isEmpty() {
        for (int x = 0; x < size - 1; x++) {
            for (int y = 0; y < size - 1; y++) {
//                System.out.println("x,y: " + x +", " + y + "\n occupied tile type: " + gameBoard[x][y].getTileType() + " key: " + gameBoard[x][y].getKey());
                if (gameBoard[x][y].getTileType() != TileTypes.WATER) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayBoard() {
        String divider = "  +";
        String h_margin = "   "; // horizontal margin

        for (int i = 0; i < size; i++) { //making the margin the size of the board
            if (i < 10) {
                h_margin += " ";
            }
            divider += "---";
            h_margin += Integer.toString(i) + " ";

            if (i == size - 1) {
                divider += "+ ";
            }
        }

        System.out.println(h_margin);
        System.out.println(divider);
        int counter = 0;

        for (int x = 0; x < size; x++) {
            System.out.print(counter + " |"); // vertical margin
            for (int y = 0; y < size; y++) {
                System.out.print(gameBoard[y][x].getKey() + " ");
            }
            System.out.println("|");
            counter++;
        }

        System.out.println(divider);
        System.out.println(h_margin);
    }
}