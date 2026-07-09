package com.interview.dp;

public class PaintHouse {
    public int minCost(int[][] costs) {
        return helper(costs, 0, 0, new Integer[costs.length][4]);
    }

    private int helper(int[][] costs, int idx, int currHouse, Integer[][] memo) {
        if (idx >= costs.length) return 0;
        if (memo[idx][currHouse] != null) return memo[idx][currHouse];
        int minCost = Integer.MAX_VALUE;

        for (int i=0; i<3; i++) {
            if (i != (currHouse - 1)) {
                minCost = Math.min(minCost, costs[idx][i]
                        + helper(costs, idx + 1, i + 1, memo));
            }
        }

        return memo[idx][currHouse] = minCost;
    }

    public int minCostPractice(int[][] cost) {
        return dfs(cost, 0, -1);
    }

    private int dfs(int[][] cost, int idx, int prevIdx) {
        if (idx >= cost.length) return 0;

        int[] price = cost[idx];
        int minCost = Integer.MAX_VALUE;

        for (int i=0; i<3; i++) {
            if (i != prevIdx) {
                minCost = Math.min(price[i] + dfs(cost, idx + 1, i), minCost);
            }
        }

        return minCost;
    }
}
