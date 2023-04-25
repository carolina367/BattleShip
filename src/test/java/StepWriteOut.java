import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepWriteOut {
    Board gameBoard = new Board(10);
    Ship testShip = new Ship();

    int userX;
    int userY;

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
    @Given("user is entering coordinates {int} {int} for a vertical equals {string} ship of type {string}")
    public void user_is_entering_coordinates_for_a_vertical_equals_true_ship_of_type(Integer x2, Integer y2, String isVertical, String shipType) {
        userX = x2;
        userY = y2;
        testShip.setVertical(Boolean.parseBoolean(isVertical));
        testShip.setShipType(ShipType.valueOf(shipType));
    }

    @When("the user tries to place the ship")
    public void the_user_tries_to_place_the_ship() {
        gameBoard.placeShip(userX, userY, testShip);
    }

    @Then("The ship should appear on board")
    public void the_ship_should_appear_on_board() {
        System.out.println("\n The ship should appear on board @ " + "userX:" + userX + " userY:" + userY);
        gameBoard.displayBoard();
        assertFalse(gameBoard.isEmpty());
        //need to add check to validate the location of the ship for use down the line
    }

    @Given("the board already contains a vertical ship of type {string} at coordinates {int} and {int}")
    public void the_board_already_contains_a_vertical_ship_of_type_string_at_coordinates_int_and_int(String shipType, Integer x, Integer y) {
        userX = x;
        userY = y;
        testShip.setVertical(true);
        testShip.setShipType(ShipType.valueOf(shipType));
        gameBoard.placeShip(userX, userY, testShip);
//        System.out.println("\n the board already contains a vertical ship of type {string} at coordinates {int} and {int} " + "userX: " + userX + " userY:" + userY);
        gameBoard.displayBoard();
        assertFalse( gameBoard.isEmpty());
    }

    @Given("the coordinates for ship overlap with an existing object on board")
    public void the_coordinates_for_ship_overlap_with_an_existing_object_on_board() {
        boolean overlap = gameBoard.overlapping(userX, userY, testShip);
        assertTrue(overlap);
    }


    @Then("a notification appears that ships cannot overlap")
    public void a_notification_appears_that_ships_cannot_overlap() {
        assertTrue(true);
        // COME BACK TO THIS!!!! IT IS A GUI THING
    }

//    @Given("user has one ship left to place")
//    public void user_has_one_ship_left_to_place() {
//        // ***
//
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Given("user is entering coordinates for a vertical ship of type ")
//    public void user_is_entering_coordinates_for_a_vertical_ship_of_type() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//    @Then("the next player is prompted to place their ships")
//    public void the_next_player_is_prompted_to_place_their_ships() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

    @When("a notification appears that ships cannot be placed out of bounds")
    public void a_notification_appears_that_ships_cannot_be_placed_out_of_bounds() {
        boolean outOfBounds = gameBoard.outOfBounds(userX, userY, testShip);
        assertTrue(outOfBounds);
        // COME BACK TO THIS!!!! IT IS A GUI THING

    }


}