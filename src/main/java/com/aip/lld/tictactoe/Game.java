package com.aip.lld.tictactoe;

public class Game {
    Board board;
    Player[] players;
    WinnerStrategy winnerStrategy;
    private int currentPlayerIdx = 0;

    public Game(Board board, Player[] players, WinnerStrategy winnerStrategy) {
        this.board = board;
        this.players = players;
        this.winnerStrategy = winnerStrategy;
    }

    public boolean makeMove(int row, int col) {
        if (!this.board.validateCell(row, col)) {
            System.out.println("Please enter valid cell");
            return false;
        }

        Player player = players[currentPlayerIdx];
        this.board.makeMove(row, col, player.symbol);

        return true;
    }

    public boolean checkWinner(int row, int col) {
        Player player = players[currentPlayerIdx];
        currentPlayerIdx = (currentPlayerIdx + 1) % players.length;
        boolean hasWinner = winnerStrategy.checkWinner(board, player, row, col);


        if (hasWinner) {
            System.out.println("Player: " + player.name + " wins");
        }

        return hasWinner;
    }

    public boolean isGameOver() {
        return !this.board.hasEmptyCells();
    }
}
