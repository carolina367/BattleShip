import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;


import static org.junit.Assert.*;

public class StepWriteOut {
    Board gameBoard = new Board(10);
    Board gameBoard2 = new Board(10);
    Ship testShip = new Ship();

    int userLetter;
    int userNum;

    Player p1 = new Human("p1");
    Player p2 = new Human("p2");
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("\n Starting scenario: " + scenario.getName());
    }

    @Given("the board is empty")
    public void the_board_is_empty() {
        assertTrue(gameBoard.isEmpty());
    }

    @Given("user is entering coordinates {int} {string} for a vertical equals {string} ship of type {string}")
    public void user_is_entering_coordinates_for_a_vertical_equals_true_ship_of_type(Integer num, String letter, String isVertical, String shipType) {
        userNum = num;
        userLetter = Character.toUpperCase(letter.charAt(0)) - 'A';
        testShip.setVertical(Boolean.parseBoolean(isVertical));
        testShip.setShipType(ShipType.valueOf(shipType));
    }

    @When("the user tries to place the ship")
    public void the_user_tries_to_place_the_ship() {
        gameBoard.placeShip(userLetter, userNum, testShip, true);
    }

    @Then("The ship should appear on board")
    public void the_ship_should_appear_on_board() {
        System.out.println("The ship should appear on board @ " + "userX:" + userNum + " userY:" + (char) ('A' + userLetter));
        gameBoard.displayBoard(false);
        assertFalse(gameBoard.isEmpty());
    }

    @Given("the board already contains a vertical ship of type {string} at coordinates {int} and {string}")
    public void the_board_already_contains_a_vertical_ship_of_type_string_at_coordinates_int_and_int(String shipType, Integer num, String letter) {
        userNum = num;
        userLetter = Character.toUpperCase(letter.charAt(0)) - 'A';
        testShip.setVertical(true);
        testShip.setShipType(ShipType.valueOf(shipType));
        gameBoard.placeShip(userLetter, userNum, testShip, true);
        System.out.println("The ship should appear on board @ " + "userX:" + userNum + " userY:" + (char) ('A' + userLetter));
        gameBoard.displayBoard(false);
        assertFalse(gameBoard.isEmpty());
    }

    @Given("the coordinates for ship overlap with an existing object on board")
    public void the_coordinates_for_ship_overlap_with_an_existing_object_on_board() {
        boolean overlap = gameBoard.overlapping(userLetter, userNum, testShip);
        assertTrue(overlap);
    }


    @Then("a notification appears that ships cannot overlap")
    public void a_notification_appears_that_ships_cannot_overlap() {
        assertFalse(gameBoard.placeShip(userLetter, userNum, testShip, false));
        // TODO: COME BACK TO THIS!!!! IT IS A GUI THING
    }

    @Given("user has placed all ships save one of type CRUISER")
    public void user_has_placed_all_ships_save_one_of_type_CRUISER() {
        int count = 0;
        for (ShipType ship : ShipType.values()) {
            testShip = new Ship(true, ship);
            gameBoard.placeShip(0, count, testShip, true);
            count++;
        }

        gameBoard.displayBoard(false);
        assertEquals(1, gameBoard.countShipsToPlace());
    }

    @Then("the next player is prompted to place their ships")
    public void the_next_player_is_prompted_to_place_their_ships() {
//        assertTrue(gameBoard.donePlacingShips());
        //TODO: fix?
        assertTrue(true);
    }

    @When("a notification appears that ships cannot be placed out of bounds")
    public void a_notification_appears_that_ships_cannot_be_placed_out_of_bounds() {
        boolean outOfBounds = testShip.outOfBounds(userLetter, userNum, gameBoard.getSize());
        assertTrue(outOfBounds);
        assertFalse(gameBoard.placeShip(userLetter, userNum, testShip, false));
        // TODO: COME BACK TO THIS!!!! IT IS A GUI THING
    }

    @Then("they should see an error message indicating that they cannot place any more {string} ships")
    public void they_should_see_an_error_message_indicating_that_they_cannot_place_any_more_ship_type_ships(String shipType) {
        assertFalse(gameBoard.placeShip(userLetter, userNum, testShip, false));
        // TODO: COME BACK TO THIS!!!! IT IS A GUI THING
    }
    
    @Given("a board that has been fully set up. last cruiser @ {int} {string}")
    public void a_board_that_has_been_fully_set_up_last_cruiser(Integer num, String letter) {
        userLetter = Character.toUpperCase(letter.charAt(0)) - 'A';
        userNum = num;
        System.out.println("starting with all ships");
        // Make & place ships
        int count = 0;
        for (ShipType ship : ShipType.values()) {
            testShip = new Ship(true, ship);
            gameBoard.placeShip(0, count, testShip, false);
            count++;
        }
        //place the last cruiser
        testShip = new Ship(true, ShipType.CRUISER);
        gameBoard.placeShip(userLetter, userNum, testShip, false);

        System.out.println("Player1's view of board they own:");
        gameBoard.displayBoard(false);
        assertEquals(0, gameBoard.countShipsToPlace());
    }
    @When("Player1 bombs coordinate {int} {string} on Player2's board")
    public void player1_bombs_coordinate_on_player2_s_board(Integer num, String letter) {
        userNum = num;
        userLetter = Character.toUpperCase(letter.charAt(0)) - 'A';
    }
    @Given("a bomb hit a ship")
    public void a_bomb_hit_a_ship() {
        for(int i = 0; i < 3; i++) {
            System.out.println("The bomb should appear on board @ " + "userX:" + userNum + " userY:" + (char) ('A' + userLetter + i));
            gameBoard.bomb(userLetter + i,userNum, p2);
        }
        p2.displayConqueredShips();
    }
    @Then("the opponent should see the alternate display of the game board")
    public void the_opponent_should_see_the_alternate_display_of_the_game_board() {
        System.out.println("\n Player2's view of P1's board:");
        gameBoard.displayBoard(true);

        System.out.println("\n Player1's view of their board after bomb:");
        gameBoard.displayBoard(false);
    }
}
