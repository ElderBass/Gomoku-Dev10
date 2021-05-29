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
        System.out.println(newGame.getCurrent().getName() + " will go first.");
        System.out.println();
        runGame(newGame, console);
    }

    private void runGame(Gomoku game, Scanner console) {
        Result res;
        GameBoard board = new GameBoard();
        while(!game.isOver()) {
            board.printGameBoard(game.getStones());
            String currentPlayer = game.getCurrent().getName();
            System.out.println("It is " + currentPlayer + "'s turn.");
            System.out.println();
            int row;
            int col;

            Stone currentTurn = game.getCurrent().generateMove(game.getStones());
            // TODO could maybe wrap this into a method...later...
            if (currentTurn == null) {
                System.out.print("Please enter the row [1-15]: ");
                while(!console.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    System.out.print("Please enter the row [1-15]: ");
                    console.next();
                }
                row = console.nextInt();
                System.out.print("Please enter the column [1-15]: ");
                while(!console.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    System.out.print("Please enter the column [1-15]: ");
                    console.next();
                }
                col = console.nextInt();
                if (game.isBlacksTurn()) {
                    currentTurn = new Stone(row, col, true);
                } else {
                    currentTurn = new Stone(row, col, false);
                }
            }
            res = game.place(currentTurn);
            if (res.getMessage() == null && res.isSuccess()) {
                System.out.println(currentPlayer + " moved successfully. Next player.");
                System.out.println();
            } else {
                System.out.println(res.getMessage());
            }
        }
        confirmGameExit(console);
    }

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
        console.nextLine();
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

    private void confirmGameExit(Scanner console) {
        // TODO this is skipping this first scanner input and I'm not sure why
        boolean isValid = false;
        String choice = "";
        while(!isValid) {
            System.out.print("Would you like to start a new game [y/n]? ");
            choice = console.nextLine();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")) {
                isValid = true;
            } else {
                System.out.println();
                System.out.println("I'm sorry, but that is not a valid answer. Please try again.");
            }
        }
        if (choice.equalsIgnoreCase("y")) {
            System.out.println();
            System.out.println("Splendid! Resetting the game board now...");
            startGame(console);
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println();
            System.out.println("Thanks for playing!");
            System.exit(0);
        }

    }
}
