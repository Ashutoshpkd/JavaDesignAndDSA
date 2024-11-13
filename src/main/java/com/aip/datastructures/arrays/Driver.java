package com.aip.datastructures.arrays;

public class Driver {
    public static void main(String[] args) {
        int[] arr = new int[] {2,9,2,5,6};
        int left = 2, right = 8;
        MaxSubArray msa = new MaxSubArray();
        System.out.println(msa.numSubarrayBoundedMax(arr, left, right));

    }
}
