package learn.gomoku.UI;

import learn.gomoku.game.Stone;

import java.util.List;

public class GameBoard {

    private String blackSymbol = " O ";
    private String whiteSymbol = " X ";
    private String emptySymbol = " _ ";

    public void printGameBoard(List<Stone> stones) {
        System.out.println();
        int count = 1;
        System.out.println("  01 02 03 04 05 06 07 08 09 10 11 12 13 14 15");
        for (int row = 0; row < 15; row++) {
            printRowNumber(count);
            for (int col = 0; col < 15; col++) {
                if (getStoneAtPosition(stones, row, col) == null) {
                    System.out.print(emptySymbol);
                } else if (getStoneAtPosition(stones, row, col).isBlack()) {
                    System.out.print(blackSymbol);
                } else if (!getStoneAtPosition(stones, row, col).isBlack()) {
                    System.out.print(whiteSymbol);
                }
            }
            count++;
            System.out.println();
        }
        System.out.println();
    }

    public Stone getStoneAtPosition(List<Stone> stones, int row, int col) {
        for (Stone stone : stones) {
            if (stone.getRow() == row && stone.getColumn() == col) {
                return stone;
            }
        }
        return null;
    }
    public void printRowNumber(int count) {
        if (count >= 1 && count < 10) {
            System.out.print("0" + count);
        } else if (count >= 10) {
            System.out.print(count);
        }
    }
}
