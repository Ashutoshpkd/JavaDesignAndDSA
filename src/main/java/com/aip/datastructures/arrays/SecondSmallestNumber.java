package com.aip.datastructures.arrays;

public class SecondSmallestNumber {
    public static void main(String[] args) {
        System.out.println(secondSmallest(new int[] {1,2, 1, 1, 1, 1}));
    }
    
    private static int secondSmallest(int[] nums) {
        if (nums.length < 2) return -1;

        int smallest = Integer.MAX_VALUE, secondSmallest = Integer.MAX_VALUE;

        for (int n : nums) {
            if (smallest > n) {
                secondSmallest = smallest;
                smallest = n;
            }

            if (secondSmallest > n && smallest != n) {
                secondSmallest = n;
            }
        }

        return secondSmallest;
    }
}
