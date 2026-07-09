package com.aip.lld.tictactoe;

import java.util.Scanner;

public class TicTacToeDriver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = 3;

        Board board = new Board(size);

        Player p1 = new Player("Player1", Symbol.X);
        Player p2 = new Player("Player2", Symbol.O);

        Player[] players = {p1, p2};

        WinnerStrategy winnerStrategy = new EfficientWinningStrategy(3);

        Game game = new Game(board, players, winnerStrategy);
        GameController gc = new GameController(game);

        while (!gc.isGameOver) {

            printBoard(board);

            System.out.println("Enter row and column (0-" + (size - 1) + ")");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            gc.makeMove(row, col);
        }

        printBoard(board);

        scanner.close();
    }

    private static void printBoard(Board board) {

        System.out.println("\nBoard:");

        for (int r = 0; r < board.size; r++) {

            for (int c = 0; c < board.size; c++) {

                Symbol symbol = board.grid[r][c].symbol;

                if (symbol == Symbol.EMPTY)
                    System.out.print("- ");
                else
                    System.out.print(symbol + " ");
            }

            System.out.println();
        }

        System.out.println();
    }
}