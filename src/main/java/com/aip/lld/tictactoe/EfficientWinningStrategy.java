package com.aip.lld.tictactoe;


import java.util.Arrays;

public class EfficientWinningStrategy implements WinnerStrategy {
    private int[] row;
    private int[] col;
    private int size;
    private int diagonal;
    private int antiDiagonal;

    public EfficientWinningStrategy(int size) {
        this.size = size;
        row = new int[size];
        col = new int[size];
        diagonal = 0;
        antiDiagonal = 0;
    }

    @Override
    public boolean checkWinner(Board board, Player player, int row, int col) {
        int val = player.symbol == Symbol.X ? 1 : -1;

        this.row[row] += val;
        this.col[col] += val;

        if (row == col) diagonal += val;

        if (row + col == size - 1) antiDiagonal += val;

        return checkWinner(row, col);
    }

    private boolean checkWinner(int r, int c) {
        return (Math.abs(row[r]) == size || Math.abs(col[c]) == size
        || Math.abs(diagonal) == size || Math.abs(antiDiagonal) == size);
    }
}
