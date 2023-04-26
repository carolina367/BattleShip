# write the feature name.feature to get this type of file
@tag
Feature: Placing ships
  @tag
  Scenario Outline: User places ship on valid position on the board
    Given the board is empty
    And user is entering coordinates <x2> <y2> for a vertical equals <isVertical> ship of type <shipType>
    When the user tries to place the ship
    Then The ship should appear on board
    Examples:
      | x2 | y2 | shipType    | isVertical |
      | 0  | 1  | "DESTROYER" | "false"    |
      | 6  | 6  | "CRUISER"   | "true"     |

  @tag
  Scenario Outline: Overlapping ships
    Given the board already contains a vertical ship of type <shipType> at coordinates <x> and <y>
    And user is entering coordinates <x2> <y2> for a vertical equals <isVertical> ship of type <shipType>
    And the coordinates for ship overlap with an existing object on board
    When the user tries to place the ship
    And a notification appears that ships cannot overlap

    Examples:
      | x | y | x2 | y2 | shipType    | isVertical |
      | 2 | 3 | 2  | 3  | "DESTROYER" | "true"     |
      | 3 | 6 | 3  | 7  | "CRUISER"   | "false"    |

  @tag
  Scenario Outline: Place ship out of bounds
    Given the board is empty
    And user is entering coordinates <x2> <y2> for a vertical equals <isVertical> ship of type <shipType>
    When the user tries to place the ship
    And a notification appears that ships cannot be placed out of bounds

    Examples:
      | x2 | y2 | shipType    | isVertical |
      | 9  | 1  | "DESTROYER" | "false"    |
      | 6  | 8  | "CRUISER"   | "true"     |

  Scenario Outline: All ships placed for first player
    Given user has one ship left to place
    And user is entering coordinates <x2> <y2> for a vertical equals <isVertical> ship of type <shipType>
    When the user tries to place the ship
    Then The ship should appear on board
    Then the next player is prompted to place their ships

    Examples:
      | x2 | y2 | shipType    | isVertical |
      | 5  | 0  | "DESTROYER" | "true"     |


#  @tag
#  Scenario: All ships placed for second player
#    Given The user places all ships
#    When the user is done placing all of their ships
#    Then

