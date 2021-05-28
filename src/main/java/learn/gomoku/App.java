package learn.gomoku;

import learn.gomoku.UI.GameMenu;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        GameMenu menu = new GameMenu();
        menu.startGame(console);

    }
}
