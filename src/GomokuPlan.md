# Gomoku Assessment Plan

## Overall Flow
* Start the game in App class --> prompt user to choose player types
  * If human player chosen --> prompt user to enter the player's name
  * If random --> move to next choice
* Once two players are added, initialize the game
* Print the game board to the screen and let user know whose turn it is
* Run the game in a game loop until it is over
    * Prompt appropriate player for two inputs: row and column they wish to move to
    * Once valid input of move is achieved, update game board, print new board, then switch players
    * Stay in loop doing these tasks until game is over
* Once game is over, exit the loop and run an exit menu function
  * Prompt the user to confirm if they wish to restart the game or exit the program
  * Restart game if they wish by running the intial method again
  * Exit program if they wish to end

## GameMenu Class
### Overview
* Prompts for selection of players on start
* Two players must be selected - can be either human or random
* Capture each selection and based on choice, create new instance of appropriate Player class (Human or Random)
* Pass these classes into Gomoku constructor to create a new game
* Render a new GameBoard class as well (detailed below)
* Print starting game board and prompt the first player for input
* Pass input into relevant Gomoku methods, display a message based on result of move
  * e.g. capture the "Result" message from the .place() method in Gomoku
  * Display this message in an appropriate way (e.g. "Oops! Wrong Player")
  * If input is determined invalid, re-run move prompt
  * Need separate prompts for row and column of stone
* Re-render gameboard after each move with updated symbol map
* Switch to next player, displaying their name and prompting for input
* Continue this pattern until isOver
  * Might want to set up the whole game inside a while loop, i.e. while(!game.isOver) {play game}
* Once game is over, display relevant message and ask user if they wish to play again
  * If they do, restart game from scratch, calling the initializing method once more
  * If not, exit program
  ### Fields (very much subject to change, just a rough idea)
  * **Player player 1** = new Player() -- make it an interface to account for both potential possibilities
  * **Player player 2** = new Player() -- same as above
  * **Gomoku game** = new Gomoku(Player1, Player2) -- we will eventually pass in the players, obviously not right away
  * **boolean gameIsOver** = false; -- we will use this for our game loop
  * May need a few more, but I'm thinking these three for sure
  ### Methods Needed (tentative list - names not final)
* **setters and getters** for our player1, player2, and game fields (or maybe not...? We'll see.);
    * Constructor...? even necessary...?
* **startGame()** - this will be run right away to capture the user's player choices
  * Might even have a **choosePlayer()** method that this startGame() method calls twice
  * choosePlayer will return either a new HumanPlayer or RandomPlayer
  * Grab these two player choices, then pass them into the Gomoku field
  
* **runGame()** -- called inside startGame() after players have been established
  * will have a while loop that will continue calling **handlePlayerTurn()** (see below) until gameIsOver is set to true
    * gameIsOver will be set to true once Gomoku's isOver variable is true
    * This may be redundant but I'm going to stick with it for now
  * Once gameIsOver = true; exit while loop and call **confirmGameExit()** (below)
* **handlePlayerTurn()** -- prompts user for input on their turn
  * Passes user's input into Gomoku.place() --> captures message after turn is done
  * If message is null, print some generic message like "Player 1 moved to [row, col]"
    * May need another while loop to keep prompting user for a valid move until one is made
  * This method will be wrapped in a while loop inside **runGame** until the game is over 
* **confirmGameExit()** -- to be run after the field gameIsOver is set to true
  * Prompt the user with a message asking them if they want to restart the game or exit the program
  * capture user's choice and either System.exit(0) or call **startGame()** again
* **choosePlayer()** -- simple method for creating a new player
  * Prompt user for what kind of player they want
  * Pass choice into switch statement (e.g. 1 = Human, 2 = Random)
  * Switch cases will instantiate the correct player type
  * this method will return a Player class of the type the user chooses

## GameBoard Class
### Overview
* Basically exactly what it says - just a class for updating and printing the game board
* Needs to print an appropriate symbol for each player (E.g. player1 = O, player2 = X)
* Empty spaces on board will be underscores
* Board will have a legend saying which symbol belongs to which player
* Board will have numbers by each col and grid marking their spots

### Fields
* Some field for managing the board's state...I'm not entirely sure about this yet but I'm thinking a HashMap
  * e.g. have the keys be the position on the board ("0105") and the values be the symbol "X", "O", or "_"
  * by default all the values will be "_"
* That might be the only field necessary honestly

### Methods
* **printGameBoard()** -- basically exactly what it sounds like
  * Will capture the state from the HashMap and print the appropriate characters in a grid style
  * Still need to work out exactly how this grid will work and how to print it but shouldn't be too bad
* **updateGameBoard()** -- essentially a setter for the gameboard HashMap state
  * will take in input from the user's move, once it's valid, and update the place on the board with that player's symbol
  * this will return a new updated HashMap of the gameboard, to be passed into printGameBoard
    * Might not even need to pass in as an argument and just have printBoard grab the updated field

  
