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
      | x2 | y2  | shipType    | isVertical |
      | 0  | "B" | "DESTROYER" | "true"     |
      | 4  | "G" | "CRUISER"   | "true"     |

  @tag
  Scenario Outline: Overlapping ships
    Given the board already contains a vertical ship of type <shipType> at coordinates <x> and <y>
    And user is entering coordinates <x2> <y2> for a vertical equals <isVertical> ship of type <shipType>
    And the coordinates for ship overlap with an existing object on board
    When the user tries to place the ship
    And a notification appears that ships cannot overlap

    Examples:
      | x | y   | x2 | y2  | shipType  | isVertical |
      | 2 | "A" | 2  | "A" | "CRUISER" | "true"     |
      | 3 | "H" | 3  | "I" | "CRUISER" | "false"    |

  @tag
  Scenario Outline: Place ship out of bounds
    Given the board is empty
    And user is entering coordinates <x2> <y2> for a vertical equals <isVertical> ship of type <shipType>
    When the user tries to place the ship
    And a notification appears that ships cannot be placed out of bounds

    Examples:
      | x2 | y2  | shipType    | isVertical |
      | 9  | "B" | "DESTROYER" | "false"    |
      | 6  | "I" | "CRUISER"   | "true"     |

  Scenario Outline: All ships placed for first player
    Given user has placed all ships save one of type destroyer
    And user is entering coordinates <x2> <y2> for a vertical equals <isVertical> ship of type <shipType>
    When the user tries to place the ship
    Then The ship should appear on board
    Then the next player is prompted to place their ships

    Examples:
      | x2 | y2  | shipType    | isVertical |
      | 5  | "A" | "DESTROYER" | "true"     |

  Scenario Outline: Placing too many of a certain ship
    Given the board already contains a vertical ship of type <shipType> at coordinates <x> and <y>
    And user is entering coordinates <x2> <y2> for a vertical equals <isVertical> ship of type <shipType>
    When the user tries to place the ship
    Then they should see an error message indicating that they cannot place any more <ship type> ships

    Examples:
      | x2 | y2  | shipType     | isVertical | x | y   |
      | 6  | "G" | "BATTLESHIP" | "false"    | 3 | "A" |

#  @tag
#  Scenario: All ships placed for second player
#    Given The user places all ships
#    When the user is done placing all of their ships
#    Then

