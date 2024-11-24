package com.aip.datastructures.dp;

public class Fibonacci {
    public static void main(String[] args) {
        int fib = 7;
        Integer dp[] = new Integer[fib + 1];
        System.out.println("Fib " + fib + " : " + getNthFibonacciNumber(fib, dp));
        System.out.println("Fib True DP of " + fib + " : " + trueDp(fib));
    }

    private static int getNthFibonacciNumber(int n, Integer[] dp) {
        if (n <= 1) return 1;
        if (dp[n] != null) return dp[n];

        return dp[n] = getNthFibonacciNumber(n - 1, dp) + getNthFibonacciNumber(n - 2, dp);
    }

    private static int trueDp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i=2; i<=n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
