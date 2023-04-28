import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;


import static org.junit.Assert.*;

public class StepWriteOut {
    Board gameBoard = new Board(10);
    Ship testShip = new Ship();

    int userX;
    int userY;
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("\n Starting scenario: " + scenario.getName());
    }

    @Given("the board is empty")
    public void the_board_is_empty() {
        assertTrue(gameBoard.isEmpty());
    }

//    @Given("random coordinates for a vertical ship")
//    public void random_coordinates_for_a_vertical_ship() {
//        userX = ThreadLocalRandom.current().nextInt(0, gameBoard.getSize() - 1);
//        userY = ThreadLocalRandom.current().nextInt(0, gameBoard.getSize() - testShip.getLength() - 1);
//        testShip.setVertical(true);
//        System.out.println("user is entering coordinates for a vertical ship" + "userX:" + userX + " userY:" + userY);
//    }
    @Given("user is entering coordinates {int} {string} for a vertical equals {string} ship of type {string}")
    public void user_is_entering_coordinates_for_a_vertical_equals_true_ship_of_type(Integer x, String y, String isVertical, String shipType) {
        userX = x;
        userY = Character.toUpperCase(y.charAt(0)) - 'A';
        testShip.setVertical(Boolean.parseBoolean(isVertical));
        testShip.setShipType(ShipType.valueOf(shipType));
    }

    @When("the user tries to place the ship")
    public void the_user_tries_to_place_the_ship() {
        gameBoard.placeShip(userX, userY, testShip);
    }

    @Then("The ship should appear on board")
    public void the_ship_should_appear_on_board() {
        System.out.println("The ship should appear on board @ " + "userX:" + userX + " userY:" + (char) ('A' + userY));
        gameBoard.displayBoard(false);
        assertFalse(gameBoard.isEmpty());
        //TODO: need to add check to validate the location of the ship for use down the line
    }

    @Given("the board already contains a vertical ship of type {string} at coordinates {int} and {string}")
    public void the_board_already_contains_a_vertical_ship_of_type_string_at_coordinates_int_and_int(String shipType, Integer x, String y) {
        userX = x;
        userY = Character.toUpperCase(y.charAt(0)) - 'A';
        testShip.setVertical(true);
        testShip.setShipType(ShipType.valueOf(shipType));
        gameBoard.placeShip(userX, userY, testShip);
        System.out.println("The ship should appear on board @ " + "userX:" + userX + " userY:" + (char) ('A' + userY));
        gameBoard.displayBoard(false);
        assertFalse(gameBoard.isEmpty());
    }

    @Given("the coordinates for ship overlap with an existing object on board")
    public void the_coordinates_for_ship_overlap_with_an_existing_object_on_board() {
        boolean overlap = gameBoard.overlapping(userX, userY, testShip);
        assertTrue(overlap);
    }


    @Then("a notification appears that ships cannot overlap")
    public void a_notification_appears_that_ships_cannot_overlap() {
        assertFalse(gameBoard.placeShip(userX, userY, testShip));
        // TODO: COME BACK TO THIS!!!! IT IS A GUI THING
    }

    @Given("user has placed all ships save one of type CRUISER")
    public void user_has_placed_all_ships_save_one_of_type_CRUISER() {
        int count = 0;
        for (ShipType ship : ShipType.values()) {
            testShip = new Ship(true, ship);
            gameBoard.placeShip(count, 0, testShip);
            count++;
        }

        gameBoard.displayBoard(false);
        assertEquals(1, gameBoard.countShipsToPlace());
    }

    @Then("the next player is prompted to place their ships")
    public void the_next_player_is_prompted_to_place_their_ships() {
        // TODO
        assertTrue(true);
    }

    @When("a notification appears that ships cannot be placed out of bounds")
    public void a_notification_appears_that_ships_cannot_be_placed_out_of_bounds() {
        boolean outOfBounds = gameBoard.outOfBounds(userX, userY, testShip);
        assertTrue(outOfBounds);
        assertFalse(gameBoard.placeShip(userX, userY, testShip));
        // TODO: COME BACK TO THIS!!!! IT IS A GUI THING
    }

    @Then("they should see an error message indicating that they cannot place any more <ship type> ships")
    public void they_should_see_an_error_message_indicating_that_they_cannot_place_any_more_ship_type_ships() {
        assertFalse(gameBoard.placeShip(userX, userY, testShip));
        // TODO: COME BACK TO THIS!!!! IT IS A GUI THING
    }

    @Given("a board that has been fully set up. last cruiser @ {int} {string}")
    public void a_board_that_has_been_fully_set_up_last_cruiser(Integer x, String yString) {
        int y = Character.toUpperCase(yString.charAt(0)) - 'A';
        System.out.println("starting with all ships");
        // Make & place ships
        int count = 0;
        for (ShipType ship : ShipType.values()) {
            testShip = new Ship(true, ship);
            gameBoard.placeShip(count, 0, testShip);
            count++;
        }
        //place the last cruiser
        testShip = new Ship(true, ShipType.CRUISER);
        gameBoard.placeShip(x, y, testShip);

        System.out.println("Player1's view of board they own:");
        gameBoard.displayBoard(false);
        assertEquals(0, gameBoard.countShipsToPlace());
    }
    @When("Player1 bombs coordinate {int} {string} on Player2's board")
    public void player1_bombs_coordinate_on_player2_s_board(Integer x, String y) {
        userX = x;
        userY = Character.toUpperCase(y.charAt(0)) - 'A';
    }
    @Given("a bomb hit a ship")
    public void a_bomb_hit_a_ship() {
        System.out.println("The bomb should appear on board @ " + "userX:" + userX + " userY:" + (char) ('A' + userY));
        for(int i = -1; i < 3; i++) {
            gameBoard.bomb(userX,userY+i);
        }
        gameBoard.bomb(userX,userY);
    }
    @Then("the opponent should see the alternate display of the game board")
    public void the_opponent_should_see_the_alternate_display_of_the_game_board() {
        System.out.println("\n Player2's view of P1's board:");
        gameBoard.displayBoard(true);

        System.out.println("\n Player1's view of their board after bomb:");
        gameBoard.displayBoard(false);
    }
}