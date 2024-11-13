package com.aip;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class Driver {
    public static void main(String[] args) {
        int[] nums = new int[] {-1, 1};

        BiConsumer<Integer, Integer> swap = (i, j) -> {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        };

        Arrays.sort(nums);
        int lastNeg = getLastNegative(nums);
        int[] negs = new int[lastNeg];

        for (int i=0; i<lastNeg; i++) negs[i] = nums[lastNeg - 1 - i];

        System.out.println(getMinFloorTravelledDistance(negs, 2)
                + getMinFloorTravelledDistance(Arrays.copyOfRange(nums, lastNeg, nums.length), 2));
    }

    private static int getMinFloorTravelledDistance(int[] nums, int capacity) {
        if (nums.length == 0) return 0;
        if (capacity >= nums.length) return Math.abs(nums[nums.length - 1] * 2);

        int idx = nums.length - 1;
        int distanceTravelled = 0;

        while (idx >= 0) {
            distanceTravelled += Math.abs(nums[idx]) * 2;
            idx -= capacity;
        }

        return distanceTravelled;
    }

    private static int getLastNegative(int[] nums) {
        int idx = 0;
        while (idx < nums.length && nums[idx] < 0) idx++;
        return idx;
    }
}