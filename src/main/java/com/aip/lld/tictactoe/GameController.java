package com.aip.lld.tictactoe;

public class GameController {
    Game game;
    boolean hasWinner;
    boolean isGameOver;

    public GameController(Game game) {
        this.game = game;
        this.hasWinner = false;
        this.isGameOver = false;
    }

    public void makeMove(int row, int col) {
        boolean isValidMove = game.makeMove(row, col);

        if (isValidMove) {
            this.hasWinner = game.checkWinner(row, col);
            this.isGameOver = this.hasWinner || game.isGameOver();
        } else {
            System.out.println("Not a valid move please try again");
        }
    }
}
