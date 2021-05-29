package learn.gomoku.UI;

import learn.gomoku.game.Stone;

import java.util.List;

public class GameBoard {

    // capture stones that players successfully place
    // pass these stones into the print board method
    // might be in the form of the array of stones...?
    // triple for-loop!
    private String blackSymbol = " O ";
    private String whiteSymbol = " X ";
    private String emptySymbol = " _ ";
// TODO so this is printing an extra set of columns every time it renders but same number of rows...I have no idea...
    public void printGameBoard(List<Stone> stones) {
        System.out.println();
        for (int row = 1; row < 16; row++) {
            for (int col = 1; col < 16; col++) {
                if (getStoneAtPosition(stones, row, col) == null) {
                    System.out.print(emptySymbol);
                } else if (getStoneAtPosition(stones, row, col).isBlack()) {
                    System.out.print(blackSymbol);
                } else if (!getStoneAtPosition(stones, row, col).isBlack()) {
                    System.out.print(whiteSymbol);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    // maybe write another method that takes in a stone, a row, and a col, checks for a stone there, then returns the stone?
    public Stone getStoneAtPosition(List<Stone> stones, int row, int col) {
        for (Stone stone : stones) {
            if (stone.getRow() == row && stone.getColumn() == col) {
                return stone;
            }
        }
        return null;
    }
}
