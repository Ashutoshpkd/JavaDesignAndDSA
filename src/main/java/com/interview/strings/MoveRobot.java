package com.interview.strings;

public class MoveRobot {
    public String getFinalPosition(int[] initialPos, String pattern) {
        int row = initialPos[0];
        int col = initialPos[1];

        for (char direction : pattern.toCharArray()) {

            switch (direction) {
                case 'U':
                    col++;
                    break;

                case 'D':
                    col--;
                    break;

                case 'L':
                    row--;
                    break;

                case 'R':
                    row++;
                    break;

                default:
                    throw new RuntimeException("Invalid direction");

            }
        }

        return "(" + row + "," + col + ")";
    }
}
