package com.aip.lld.tictactoe;

public class Board {
    private int filledCells = 0;
    int size;
    Cell[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Cell[size][size];

        for (int r=0; r<size; r++) {
            for (int c=0; c<size; c++) {
                this.grid[r][c] = new Cell(r, c);
            }
        }
    }

    public boolean validateCell(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) return false;
        return this.grid[row][col].symbol == Symbol.EMPTY;
    }

    public void makeMove(int row, int col, Symbol symbol) {
        this.grid[row][col] = new Cell(row, col);
        this.grid[row][col].symbol = symbol;
        this.filledCells++;
    }

    public boolean hasEmptyCells() {
        return filledCells != size * size;
    }
}
