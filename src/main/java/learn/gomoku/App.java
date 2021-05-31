package learn.gomoku;

import learn.gomoku.UI.GameMenu;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        // Initialize our Scanner console
        Scanner console = new Scanner(System.in);
        // Initialize a new GameMenu class
        GameMenu menu = new GameMenu();
        // Run the startGame method from the GameMenu class, passing in our console
        menu.startGame(console);

    }
}
