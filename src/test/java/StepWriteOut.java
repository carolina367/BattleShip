import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepWriteOut {
    Board gameBoard = new Board(10);
    Ship testShip = new Ship();

    int userX;
    int userY;
    Ship testShip1 = new Ship();
    Ship testShip2 = new Ship();
    Board gameBoard1 = new Board(10);
    Board gameBoard2 = new Board(10);
    int user1X=1;
    int user1Y=1;
    int user2X=5;
    int user2Y=6;
    Player player1 = new Player("Player 1");
    Player player2 = new Player("Player 2");


    @Given("the board is empty")
    public void the_board_is_empty() {
        assertEquals(true, gameBoard.isEmpty());
    }
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
        assertEquals(false, gameBoard.isEmpty());
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
        assertEquals(false, gameBoard.isEmpty());
    }

    @Given("the coordinates for ship overlap with an existing object on board")
    public void the_coordinates_for_ship_overlap_with_an_existing_object_on_board() {
        boolean overlap = gameBoard.overlapping(userX, userY, testShip);
        assertEquals(true, overlap);
    }


    @Then("a notification appears that ships cannot overlap")
    public void a_notification_appears_that_ships_cannot_overlap() {
        assertEquals(true, true);
        // COME BACK TO THIS!!!! IT IS A GUI THING
    }

    @Given("user has one ship left to place")
    public void user_has_one_ship_left_to_place() {
        if (player1.getShipsLeftToPlace() == 1) {
            System.out.println(player1.getName() + " has one ship left to place");
        } else if (player2.getShipsLeftToPlace() == 1) {
            System.out.println(player2.getName() + " has one ship left to place");
        }
        System.out.println("Test 'user_has_one_ship_left_to_place' complete");

    }

    @Given("user is entering coordinates for a vertical ship of type ")
    public void user_is_entering_coordinates_for_a_vertical_ship_of_type() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(testShip.isVertical(), true);
        System.out.println("Test 'user_is_entering_coordinates_for_a_vertical_ship_of_type' complete");
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the next player is prompted to place their ships")
    public void the_next_player_is_prompted_to_place_their_ships() {
        // Write code here that turns the phrase above into concrete actions

        if (player1.getShipsLeftToPlace() == 0) {
            System.out.println(player2.getName() + ", it's your turn to place your ships");
            player1.switchTurns();
        } else {
            System.out.println(player1.getName() + ", it's your turn to place your ships");
        }
        System.out.print("turns switched");
    }

    @When("a notification appears that ships cannot be placed out of bounds")
    public void a_notification_appears_that_ships_cannot_be_placed_out_of_bounds() {
        assertEquals(true, true);
        // COME BACK TO THIS!!!! IT IS A GUI THING
    }
    @Given("User has placed their ships on their map")
    public void User_has_placed_their_ships_on_their_map() {
        Board gameBoard = new Board(10);
        Ship testShip = new Ship();
        int userX=5;
        int userY=6;
        testShip.setVertical(true);
        gameBoard.placeShip(userX, userY, testShip);
        assertEquals(false, gameBoard.isEmpty());
    }
    @Then("User can see the locations of their placed ships on the map")
    public void User_can_see_the_locations_of_their_placed_ships_on_the_map() {
        System.out.println("\n The board with ships should appear on board");
        gameBoard.displayBoard();
        assertEquals(false, gameBoard.isEmpty());
        //need to add check to validate the location of the ship for use down the line
    }
    @Given("Players are placing ships on their boards")
    public void Users_are_placing_ships_on_their_board() {
        testShip1.setVertical(true);
        testShip2.setVertical(true);
        System.out.print("Ship, board and coordinates declared, placing ships ready");
    }
    @When("I player1 place a ship on my private board")
    public void I_player1_place_a_ship_on_my_private_board() {
        gameBoard1.placeShip(user1X, user1Y, testShip1);
        System.out.print("Player 1 has placed their ship");
    }
    @And("Opponent player2 places their ships on their board")
    public void opponent_player2_places_their_ships_on_their_board(){
        gameBoard2.placeShip(user2X, user2Y, testShip2);
        System.out.print("Player 2 has placed their ship");
    }
    @Then("My ships are only visible for me and my foes ships to them")
    public void My_ships_are_only_visible_for_me_and_my_foes_ships_to_them() {
        System.out.print("Board of Player 1");
        gameBoard1.displayBoard();
        System.out.print("Board of Player 2");
        gameBoard2.displayBoard();
    }











}