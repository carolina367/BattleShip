Battleship Game

This is a Java implementation of the classic Battleship game. The game consists of a 10x10 grid where two players place their ships and take turns trying to hit their opponent's ships. The game ends when one player sinks all of their opponent's ships.

Getting Started:

To run the game, open the Driver.java file and run it in your Java environment. The game will prompt you to enter the names of the two players and to place their ships on the game board. Once the game starts, players will take turns selecting a tile to attack by entering its row and column coordinates.

Code Overview:
The code is divided into several classes:

Driver:
The Driver class is responsible for initializing the game and running the main game loop.

Board:
The Board class represents the game board, which is a 10x10 grid of Tile objects.

Tile:
The Tile class represents a single tile on the game board. It can be either water, a ship, or an obstacle. Each Tile object stores its type (represented by the TileType enum), a key (used for displaying the tile on the game board), and a reference to the Ship object occupying the tile (if any).

ShipType Enumeration:
The ShipType enumeration represents the different types of ships that can be placed on the game board.

TileType Enumeration:
The TileType enumeration represents the different types of tiles that can be on the game board.

Acknowledgements:
This game was created by Carolina Barboza, Sebastian Oberg, and Artur Mazurkiewicz as a project for Agile Object-oriented Software Development (02160) at Denmark Technical University.

Future Improvements:
Here are some possible improvements that could be made to the game:
Implement a graphical user interface for the game board
and add sound effects and music to make the game more immersive.

*Note: Please be advised that although we made our best efforts, the code may be incomplete due to the formation of a small group. We kindly ask that you take this into consideration when evaluating our project. Thank you for your understanding.*
