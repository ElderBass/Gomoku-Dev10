package learn.gomoku.UI;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;

public class GameMenu {

    private Player player1;
    private Player player2;
    private Gomoku newGame;
    private GameBoard board;

    private Scanner console = new Scanner(System.in);

    public void startGame(Scanner console) {
        System.out.println("Welcome to Gomoku: Java Style");
        System.out.println("=================================================");
        System.out.println("This is a two player game. Player 1 chooses first.");
        System.out.println();
        player1 = handleChoosePlayer(console);

        System.out.println("=================================================");
        System.out.println("Great. Now Player 2 chooses.");
        System.out.println();
        player2 = handleChoosePlayer(console);
        System.out.println("=================================================");

        newGame = new Gomoku(player1, player2);
        runGame(newGame);
    }

    private void runGame(Gomoku game) {
        Result res;
        while(!game.isOver()) {
            // game loop here
            // I may need to break up turnResult to make it more readable lol
            Stone currentTurn = game.getCurrent().generateMove(game.getStones());
            // TODO could maybe wrap this into a method...later...
            if (currentTurn == null) {
                System.out.print("Please enter the row [1-15]: ");
                while(!console.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    System.out.print("Please enter the row [1-15]: ");
                    console.next();
                }
            }
            int turnResult = renderGameMessage(game.place(currentTurn), game);
            if (turnResult == 0) {
                confirmGameExit(console);
            } else {
                continue;
            }
        }
        confirmGameExit(console);
    }

    // TODO renderGameMessage(Result res) needs to be written

    private Player handleChoosePlayer(Scanner console) {
        Player player = null;
        System.out.println("Please Choose A Player Type");
        System.out.println();
        System.out.println("1 - Human Player");
        System.out.println("2 - Random Player");
        System.out.println("=================================================");
        System.out.print("What is your choice [1 or 2]? ");
        while(!console.hasNextInt()) {
            System.out.println("That is not a valid choice. Please try again.");
            System.out.print("What is your choice [1 or 2]? ");
            console.next();
        }
        int choice = console.nextInt();
        switch(choice) {
            case 1:
                player = generateHumanPlayer(console);
                break;
            case 2:
                player = new RandomPlayer();
                System.out.println(player.getName() + " has been added to the game.");
                break;
            default:
                System.out.println("That is not a valid option. Please try again.");
                handleChoosePlayer(console);
                break;
        }
        return player;
    }

    private Player generateHumanPlayer(Scanner console) {
        System.out.print("Please enter a name for the player: ");
        String name = console.nextLine();
        Player human = new HumanPlayer(name);
        System.out.println(name + " has been added to the game.");
        return human;
    }
    private int renderGameMessage(Result res, Gomoku game) {
        // have this return an int, and based on the int it returns, something will happen in run game
        // 0 means the game is over, 1 means an invalid move was made
        String message = res.getMessage();
        if (message.equals("Game is over.")) {
            System.out.println(game.getWinner() + " has won the game!");
            return 0;
        } else if (message.equals("Stone is off the board") || message.equals("Wrong player.") || message.equals("Duplicate move.")) {
            System.out.println(message);
            return 1;
        } else if (message.equals("Game ends in a draw.")) {
            System.out.println(message);
            return 0;
        } else if (message.equals(game.getWinner() + " wins.")) {
            System.out.println(message);
            System.out.println();
            return 0;
        } else if (message == null && res.isSuccess()){
            return -1;
        }
    }

    private void confirmGameExit(Scanner console) {
        System.out.println("Would you like to start a new game [y/n]? ");
        String choice = console.nextLine().trim().toLowerCase();
        System.out.println();
        switch (choice) {
            case "y":
                System.out.println("Excellent! Resetting the board...");
                startGame(console);
                break;
            case "n":
                System.out.println("Thanks for playing!");
                System.exit(0);
                break;
        }
    }
}
