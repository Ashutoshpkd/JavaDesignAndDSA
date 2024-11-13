package com.interview.recursion;

public class NStairs {

    public int countWays(int stairs, int[] ways) {
        int[] dp = new int[stairs + 1];
        dp[0] = 1;

        for (int i=1; i<=stairs; i++) {
            for (int way : ways) {
                if (i - way >= 0) dp[i] += dp[i - way];
            }
        }

        return dp[stairs];
    }
}
