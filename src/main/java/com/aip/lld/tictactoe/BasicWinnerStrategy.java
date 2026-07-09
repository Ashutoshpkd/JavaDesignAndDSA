package com.aip.lld.tictactoe;

public class BasicWinnerStrategy implements WinnerStrategy {
    @Override
    public boolean checkWinner(Board board, Player player, int row, int col) {
        int size = board.size;
        Symbol symbol = player.symbol;
        Cell[][] grid = board.grid;

        boolean rowWinner = true;
        boolean colWinner = true;
        boolean diagonalWinner = true;
        boolean antiDiagonalWinner = true;

        for (int i=0; i<size; i++) {
            if (grid[i][col].symbol != symbol) rowWinner = false;

            if (grid[row][i].symbol != symbol) colWinner = false;

            if (grid[i][i].symbol != symbol) diagonalWinner = false;

            if (grid[i][size - 1 - i].symbol != symbol) antiDiagonalWinner = false;
        }

        return rowWinner || colWinner || antiDiagonalWinner || diagonalWinner;
    }
}
