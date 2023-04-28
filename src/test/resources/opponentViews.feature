# write the feature name.feature to get this type of file
@tag
Feature: Opponent Views

  @tag
  Scenario Outline: Opponent can see the alternate display of Bomb location on opponent's board
    Given a board that has been fully set up. last cruiser @ <x> <y>
    When Player1 bombs coordinate <x> <y> on Player2's board
    And a bomb hit a ship
    Then the opponent should see the alternate display of the game board

    Examples:
      | x | y   |  |  |
      | 5 | "C" |  |  |
      | 5 | "A" |  |  |
      | 3 | "C" |  |  |

    ##############################################################################################
