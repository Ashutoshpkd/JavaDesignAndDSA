package com.aip.datastructures.arrays;

public class MaxSubArray {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int totalSubArrays = 0;
        System.out.println("Character: " + Character.getNumericValue('5'));

        for (int i=0; i<nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                int count = 0;
                for (int j=i+1; j<nums.length; j++) {
                    if (nums[j] <= right) count++;
                    else break;
                }
                totalSubArrays += (int) Math.pow(2, count);
            }
        }

        return totalSubArrays;
    }
}
