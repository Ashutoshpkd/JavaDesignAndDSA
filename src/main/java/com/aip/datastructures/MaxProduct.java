package com.aip.datastructures;

public class MaxProduct {
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE, min = Integer.MAX_VALUE, temp = 1;
        int currMax = 1, currMin = 1;

        for (int i=0; i<nums.length; i++) {
            temp = currMax;
            currMax = Math.max(nums[i], Math.max(currMin * nums[i], currMax * nums[i]));
            currMin = Math.min(nums[i], Math.min(currMin * nums[i], temp * nums[i]));

            res = Math.max(res, Math.max(currMax, currMin));

            if (nums[i] == 0) {
                currMin = 1;
                currMax = 1;
            }
        }

        return res;
    }
}
