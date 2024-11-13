package com.interview.greedy;

public class BestDeal {
    public int getMaxProfitForIntervals(int[][] intervalList, int[] intervalPrice) {
        int profit = 0;
        int currProfit = 0;

        for (int i=0; i<intervalList.length; i++) {
            if (i < intervalList.length - 1 && intervalList[i][1] > intervalList[i + 1][0]) {
                currProfit = Math.max(currProfit, intervalPrice[i]);
            } else {
                currProfit = Math.max(currProfit, intervalPrice[i]);
                profit += currProfit;
                currProfit = 0;
            }
        }

        return profit;
    }
}
