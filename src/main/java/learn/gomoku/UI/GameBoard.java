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
// TODO so this is printing an extra set of columns every time it renders but same number of rows...I have no idea...
    public void printGameBoard(List<Stone> stones) {
        System.out.println();
        for (int row = 1; row < 16; row++) {
            for (int col = 1; col < 16; col++) {

                if (stones.isEmpty()) {
                    System.out.print(" _ ");
                } else {
                    for (int index = 0; index < stones.size(); index++) {
                        if (stones.get(index).isBlack() && stones.get(index).getRow() == row && stones.get(index).getColumn() == col) {
                            System.out.print(blackSymbol);
                        } else if (!stones.get(index).isBlack() && stones.get(index).getRow() == row && stones.get(index).getColumn() == col) {
                            System.out.print(whiteSymbol);
                        } else {
                            System.out.print(" _ ");
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
