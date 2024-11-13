package com.aip.math;

public class RotateImage {
    public void rotate(int[][] matrix) {
        int lastR = 0, lastC = 0;
        int l = 0, r = matrix[0].length - 1;
        int fr = 0, fc = 0;
        int idx = 0;

        while (l < r) {
            lastR = matrix.length - 1 - l;
            lastC = r;
            idx = l;
            fc = l;
            fr = l;

            while (idx < lastC) {
                int pixel = matrix[fr][idx];
                int temp = matrix[idx][lastC];
                matrix[idx][lastC] = pixel;
                pixel = temp;

                temp = matrix[lastR][lastC - idx];
                matrix[lastR ][lastC - idx] = pixel;
                pixel = temp;

                temp = matrix[lastR - idx][fc];
                matrix[lastR - idx][fc] = pixel;
                pixel = temp;

                matrix[fr][idx] = pixel;
                idx++;
            }
            l++;
            r--;
        }
    }
}
