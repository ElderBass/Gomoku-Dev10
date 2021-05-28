package learn.gomoku.UI;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;

public class GameMenu {

    private Player player1;
    private Player player2;
    private Gomoku newGame;

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

        while(!game.isOver()) {
            // game loop here
            game.place(game.getCurrent().generateMove(game.getStones()));
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
