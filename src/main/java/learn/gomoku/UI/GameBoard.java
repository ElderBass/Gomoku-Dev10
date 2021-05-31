package learn.gomoku.UI;

import learn.gomoku.game.Stone;

import java.util.List;

public class GameBoard {

    // Not really necessary to make these fields but I suppose it makes things simpler and is good practice
    private String blackSymbol = " O ";
    private String whiteSymbol = " X ";
    private String emptySymbol = " _ ";

    /** Main method for actually printing the game board's symbols in the appropriate places
     * Takes in game stones and prints either an O, X, or _ depending on what's there
     *
     * @param stones = the Gomoku game class's ArrayList of Stones representing the game's move history
     *
     */
    public void printGameBoard(List<Stone> stones) {
        System.out.println();

        // This was the only way I could hack out a "column" indicating the row number of each row - it's implemented below
        int count = 1;
        // Start by printing out a string for column numbers
        System.out.println("  01 02 03 04 05 06 07 08 09 10 11 12 13 14 15");

        // Do a nested for loop for printing out rows, then columns and their symbols
        for (int row = 0; row < 15; row++) {
            // Pass that count variable into the printRowNumber method, which will print a string indicating the row number
            // Print this before printing anything else
            printRowNumber(count);
            for (int col = 0; col < 15; col++) {
                // If there is no stone in the game's stones ArrayList that has a row and col that matches the row and col numbers here, print the empty symbol
                if (getStoneAtPosition(stones, row, col) == null) {
                    System.out.print(emptySymbol);
                    // If there is a stone at row and col and it's black, print the black symbol
                } else if (getStoneAtPosition(stones, row, col).isBlack()) {
                    System.out.print(blackSymbol);
                    // If the stone at this position isn't black, print the white symbol
                } else if (!getStoneAtPosition(stones, row, col).isBlack()) {
                    System.out.print(whiteSymbol);
                }
            }
            // Increment our count so we can print row numbers 1 - 15
            count++;
            // Need this to get to a new line, i.e. a new row
            System.out.println();
        }
        // Just some empty space once the whole board is printed
        System.out.println();
    }

    /** Custom helper method for checking if a stone is at a specific position on the board
     *
     * @param stones Gomoku game class's ArrayList of Stones, which we cross reference with row and column positions also passed in
     * @param row Which row on the Gomoku board a Stone is at
     * @param col Which column on the Gomoku board a Stone is at
     * @return Returns null if no stone was found at the row/col position passed in, or the Stone at that row/col position
     */
    public Stone getStoneAtPosition(List<Stone> stones, int row, int col) {
        // If in our stones array we find a stone at this position, return the stone
        for (Stone stone : stones) {
            if (stone.getRow() == row && stone.getColumn() == col) {
                return stone;
            }
        }
        // If no stones match that position, return null instead
        return null;
    }

    /** Simple helper method for printing the row number (e.g. "01", "15") next to its corresponding row
     *
     * @param count An integer indicating at which row number we want to print
     */
    public void printRowNumber(int count) {

        // If the count is between 1 and 10, we want to add a leading 0 for format purposes
        if (count >= 1 && count < 10) {
            System.out.print("0" + count);
        } else if (count >= 10) {
            // Can just print the count if it's a double-digit number
            System.out.print(count);
        }
    }
}
