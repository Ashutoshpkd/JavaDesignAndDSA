package com.aip.datastructures.arrays;

import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        int[] arr = new int[] {2,9,2,5,6};
        int left = 2, right = 8;
        MaxSubArray msa = new MaxSubArray();
        System.out.println(msa.numSubarrayBoundedMax(arr, left, right));
        String strNum = String.valueOf(100L);
        System.out.println(strNum);
        char[] nums = strNum.toCharArray();
        String num = String.valueOf(nums);
        System.out.println(Long.valueOf(num));
    }
}
