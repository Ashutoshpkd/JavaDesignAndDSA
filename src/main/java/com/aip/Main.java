package com.aip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Input:
//
//        Output: 98 7654 321

        System.out.println(reverse("12 3456 789"));
        System.out.println(convertToInt("4567"));
    }

    private static int convertToInt(String in) {
        double lastRes = 0;
        double pow = 0;

        for (int idx = in.length() - 1; idx >= 0; idx--) {
            int digit = in.charAt(idx) - '0';
            lastRes = Math.pow(10.0, pow) * (double) digit + (double) lastRes;
            pow++;
        }

        return (int) lastRes;
    }

    private static String reverse(String in) {
        char[] inArr = in.toCharArray();
        int l = 0;
        int r = in.length() - 1;

        while (l <= r) {
            char lCh = inArr[l];
            char rCh = inArr[r];

            if (!Character.isLetterOrDigit(lCh)) {
                l++;
                continue;
            }

            if (!Character.isLetterOrDigit(rCh)) {
                r--;
                continue;
            }
            swap(inArr, l, r);
            l++;
            r--;
        }

        return String.valueOf(inArr);
    }

    private static void swap(char[] inArr, int idx1, int idx2) {
        char temp = inArr[idx1];
        inArr[idx1] = inArr[idx2];
        inArr[idx2] = temp;
    }
}
