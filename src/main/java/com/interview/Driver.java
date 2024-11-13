package com.interview;

import com.interview.greedy.BestDeal;
import com.interview.recursion.NStairs;
import com.interview.strings.EncodeString;
import com.interview.strings.MNStringPattern;
import com.interview.strings.MoveRobot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Driver {
    public static void main(String[] args) {
        MNStringPattern mnStringPattern = new MNStringPattern();
        MoveRobot mr = new MoveRobot();
        BestDeal bestDeal = new BestDeal();
        NStairs nStairs = new NStairs();
        int[] pos = new int[] {0, 0};
        int [][] intervalList = {{1,8},{2,6},{4,8}};
        int [] intervalPrice = {50, 30, 25};
        Arrays.stream(intervalPrice).max().orElse(-1);
//        System.out.println(mnStringPattern.findPossibleSmallestNumberMatchingPattern("MMM"));
        System.out.println(mr.getFinalPosition(pos, "UDDLLRUUUDUURUDDUULLDRRRR"));
        System.out.println(bestDeal.getMaxProfitForIntervals(intervalList, intervalPrice));
        System.out.println(compress("zzzzzzz"));
        System.out.println("N-Stairs: " + nStairs.countWays(4, new int[] {1, 2, 3}));
        String s1 = "Ashu";
        String s2 = new String("Ashu");
        System.out.println(s1.equals(s2));
    }

    public static String compress(String s) {
        int i = 0, n = s.length();
        String enc = "";

        while (n > 0) {
            if (n % 2 == 0 && s.substring(i, n / 2).equals(s.substring(n / 2, n))) {
                enc = compress(s.substring(i, n / 2)) + "*" + enc;
                break;
            } else {
                enc = s.substring(n-1, n) + enc;
                n--;
            }
        }

        return enc;
    }
}
