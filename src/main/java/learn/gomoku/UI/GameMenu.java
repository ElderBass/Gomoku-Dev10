package learn.gomoku.UI;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;

public class GameMenu {

    // Three fields for this class - two players and a Gomoku game instance
    // Leave the players as the general interface to allow for choice between Human or Random players
    private Player player1;
    private Player player2;
    private Gomoku newGame;

    // Method to initialize player1, player2, and a new Gomoku game based on user input
    public void startGame(Scanner console) {
        // Welcome message
        System.out.println("Welcome to Gomoku: Java Style");
        System.out.println("=================================================");

        // Prompt user to choose their type of player
        System.out.println("This is a two player game. Player 1 chooses first.");
        System.out.println();
        // Method that returns a new player based on the user's choice (Human or Random) - set this new player to player1 field
        player1 = handleChoosePlayer(console);

        // Do the same thing for player2, calling the handleChoosePlayer and setting its result to player2
        System.out.println("=================================================");
        System.out.println("Great. Now Player 2 chooses.");
        System.out.println();
        player2 = handleChoosePlayer(console);
        System.out.println("=================================================");

        // Pass the new players into the constructor for a new Gomoku game and set this to our newGame field
        newGame = new Gomoku(player1, player2);

        // Message indicating which player was randomly selected to go first by the Gomoku class
        System.out.println(newGame.getCurrent().getName() + " will go first.");
        System.out.println();
        // Now we pass in our newGame field into the runGame method along with our console
        runGame(newGame, console);
    }

    private void runGame(Gomoku game, Scanner console) {
        // Initialize a new GameBoard instance which will track the game's state and print an updated board based on players' moves
        GameBoard board = new GameBoard();

        // Our game loop - we will stay in here until our Gomoku game.isOver boolean returns true
        while (!game.isOver()) {

            // Just prints the board and a legend with it, indicating which player has which symbol on the board
            renderBoard(game, board);

            // Method for handling a player's turn - doesn't return anything but keeps our Gomoku game updated with moves
            handlePlayerTurn(game, console);
        }
        // Print game board once more after game has finished to see the3 final result
        board.printGameBoard(game.getStones());

        // Move into the "Exit" method
        confirmGameExit(console);
    }

    // Method for creating a new Human or Random player based on user's input
    private Player handleChoosePlayer(Scanner console) {
        // Declare a new player and just set it to null for now
        Player player = null;

        // Menu for choosing a player type
        System.out.println("Please Choose A Player Type");
        System.out.println();
        System.out.println("1 - Human Player");
        System.out.println("2 - Random Player");
        System.out.println("=================================================");
        System.out.print("What is your choice [1 or 2]? ");

        // Need the user to enter an integer so stay in this loop until they do
        while (!console.hasNextInt()) {
            System.out.println("That is not a valid choice. Please try again.");
            System.out.print("What is your choice [1 or 2]? ");
            console.next();
        }
        // Once a choice was made, need to validate it and do something with it - switch statement made the most sense
        int choice = console.nextInt();
        console.nextLine();

        // Take the user's choice and create a Human or Random player, or prompt the user again if the choice was erroneous
        // Set our player variable from above to either a Human or Random player
        switch (choice) {
            case 1:
                player = generateHumanPlayer(console);
                break;
            case 2:
                player = new RandomPlayer();
                break;
            default:
                // For an erroneous choice (e.g. -1 or 14)
                System.out.println("That is not a valid option. Please try again.");
                // Just recall this method to get back to the player choice menu and try again
                handleChoosePlayer(console);
                break;
        }
        // Message indicating the player was added successfully
        System.out.println(player.getName() + " has been added to the game.");
        // Return the new player
        return player;
    }

    // Method for specifically creating a Human player and having the user name the new player
    private Player generateHumanPlayer(Scanner console) {

        // Prompt user for a name and capture it in a variable - don't really need validation since the user can name it whatever they want
        System.out.print("Please enter a name for the player: ");
        String name = console.nextLine();

        // Return the new player so handleChoosePlayer can use it
        return new HumanPlayer(name);
    }

    // Method for
    private void handlePlayerTurn(Gomoku game, Scanner console) {

        // This Result we initialize and will use to print out the corresponding message based on a player's move
        Result res;

        // Initialize these, which we will capture via user input for their move (in the case of a HumanPlayer)
        int row;
        int col;

        // Declare a new Stone which will be the current player's move on the board
        Stone currentTurn = game.getCurrent().generateMove(game.getStones());

        // One more variable we need, which is the current player's name, just for UI purposes of keeping track of whose turn it is
        String currentPlayer = game.getCurrent().getName();

        // generateMove will return a Stone that is null if it's a HumanPlayer's move
        // We have to capture the Human's input from the console to actually generate a move
        if (currentTurn == null) {
            // Prompt the user for a row and column - continue prompting until valid integer placed
            System.out.print("Please enter the row [1-15]: ");
            while (!console.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                System.out.print("Please enter the row [1-15]: ");
                console.next();
            }
            // The gameboard is managed via index count (starting at 0) so we convert the user's choice to this format
            row = console.nextInt() - 1;

            // Do the same above for the column they wish to move to
            System.out.print("Please enter the column [1-15]: ");
            while (!console.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                System.out.print("Please enter the column [1-15]: ");
                console.next();
            }
            col = console.nextInt() - 1;

            // Now, once a move is made, see if it was black's turn or not
            // Pass the relevant arguments into the Stone constructor and set this new stone to our currentTurn stone
            if (game.isBlacksTurn()) {
                currentTurn = new Stone(row, col, true);
            } else {
                currentTurn = new Stone(row, col, false);
            }
        }
        // Assign our Result variable to the Result that is return by passing our currentTurn stone into the .place method
        res = game.place(currentTurn);

        // If the res.getMessage() is null, that means a move was made successfully and the game is still going
        // So we print a message saying as much and move to the next player
        if (res.getMessage() == null && res.isSuccess()) {
            System.out.println(currentPlayer + " moved successfully. Next player.");
            System.out.println();
        } else {
            // This else statement is if something went wrong or the game ended e.g. if a player made a duplicate move or won the game
            // In those cases, the .place() method will return a Result class with a message indicating the result of that turn, which we print
            System.out.println(res.getMessage());
        }
    }

    // Simple method for rendering an updated Gomoku board based on all the moves thus far
    private void renderBoard(Gomoku game, GameBoard board) {

        // This method is explained further in the GameBoard class, but basically just prints the board with appropriately placed symbols
        board.printGameBoard(game.getStones());

        // Just a legend to indicate which player has which symbol on the board
        System.out.println(player1.getName() + " = O");
        System.out.println((player2.getName() + " = X"));

        // After printing the board, remind the user whose turn it is
        System.out.println("It is " + game.getCurrent().getName() + "'s turn.");
        System.out.println();
    }

    // Method for restarting or exiting the game based on what the user wants to do once a game finishes
    private void confirmGameExit(Scanner console) {

        // TODO this is skipping this first scanner input and I'm not sure why
        // This weirdly only skips the first scanner if there is at least one Human player. Two Randoms will not skip the first question...

        boolean isValid = false; // for staying in our loop until a correct choice is made

        // We initially assign an empty string to the user's choice but will update this inisde the while loop
        String choice = "";

        // In hindsight, could have also done this with a switch statement but this works just as well
        while (!isValid) {
            // Prompt user for input and capture it in our choice variable
            System.out.print("Would you like to start a new game [y/n]? ");
            choice = console.nextLine();

            // Validate user choice - anything other than a y or n will keep the user in the while loop
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")) {
                isValid = true;
            } else {
                System.out.println();
                System.out.println("I'm sorry, but that is not a valid answer. Please try again.");
            }
        }
        // Restart the game if the user wants to by simply calling startGame again
        if (choice.equalsIgnoreCase("y")) {
            System.out.println();
            System.out.println("Splendid! Resetting the game board now...");
            startGame(console);

            // Simply exit the program if the user choose n
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println();
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
    }
}
