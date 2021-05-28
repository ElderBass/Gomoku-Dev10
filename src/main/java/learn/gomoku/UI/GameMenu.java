package learn.gomoku.UI;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.List;
import java.util.Scanner;

public class GameMenu {

    private Player player1;
    private Player player2;
    private Gomoku newGame;
    private boolean gameIsOver = false;
    private Scanner console = new Scanner(System.in);

    public Player handleChoosePlayer(Scanner console) {
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

    public Player generateHumanPlayer(Scanner console) {
        System.out.print("Please enter a name for the player: ");
        String name = console.nextLine();
        Player human = new HumanPlayer(name);
        System.out.println(name + " has been added to the game.");
        return human;
    }


}
