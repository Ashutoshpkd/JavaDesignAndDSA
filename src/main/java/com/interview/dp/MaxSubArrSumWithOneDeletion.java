package com.interview.dp;

import java.util.Arrays;

public class MaxSubArrSumWithOneDeletion {
        public int maximumSum(int[] arr) {
            int n = arr.length;
            int[] noDeletion = new int[n];
            int[] oneDeletion = new int[n];

            // Initial values
            noDeletion[0] = arr[0];
            oneDeletion[0] = -100000;
            int maxSum = arr[0];

            for (int i = 1; i < n; i++) {
                noDeletion[i] = Math.max(Math.max(arr[i], noDeletion[i - 1] + arr[i]), noDeletion[i - 1]);
                oneDeletion[i] = Math.max(noDeletion[i - 1], oneDeletion[i - 1] + arr[i]);
                maxSum = Math.max(maxSum, Math.max(noDeletion[i], oneDeletion[i]));
            }

            System.out.println(Arrays.toString(noDeletion));

            return maxSum;
        }

        public int maxSumArr(int[] nums) {
            int currSum = 0, maxSum = Integer.MIN_VALUE;

            for (int n : nums) {
                currSum += n;
                maxSum = Math.max(currSum, maxSum);

                if (currSum < 0) currSum = 0;
            }

            return maxSum;
        }
}
