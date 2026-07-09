package com.aip.lld.tictactoe;

public interface WinnerStrategy {
    boolean checkWinner(Board board, Player player, int row, int col);
}
