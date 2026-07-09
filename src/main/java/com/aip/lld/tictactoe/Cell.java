package com.aip.lld.tictactoe;

public class Cell {
    int row;
    int col;
    Symbol symbol;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.symbol = Symbol.EMPTY;
    }

}
