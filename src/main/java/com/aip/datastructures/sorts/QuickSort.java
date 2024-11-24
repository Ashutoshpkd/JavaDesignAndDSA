package com.aip.datastructures.sorts;

import com.aip.datastructures.helpers.Helper;

public class QuickSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        int l = 0, r = arr.length - 1;
        quickSort(arr, l, r);
        return arr;
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int pIndex = partition(nums, l, r);
            quickSort(nums, l, pIndex - 1);
            quickSort(nums, pIndex + 1, r);
        }
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int pIndex = 0;

        for (int idx=0; idx<r; idx++) {
            if (pivot >= nums[idx]) {
                Helper.swap(nums, pIndex, idx);
                pIndex++;
            }
        }
        Helper.swap(nums, pIndex, r);
        return pIndex;
    }
}
