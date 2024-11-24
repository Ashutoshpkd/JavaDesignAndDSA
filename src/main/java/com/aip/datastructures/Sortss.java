package com.aip.datastructures;

import java.util.*;
import java.util.stream.Collectors;

public class Sortss {
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        QuickSort quickSort = new QuickSort();
        System.out.println(Arrays.toString(mergeSort.sort(new int[] {5,4,3,0,1})));
        System.out.println(Arrays.toString(quickSort.sort(new int[] {5,4,3,0,1})));

        Map<Integer, Integer> cache = new HashMap<>();
        List<Integer> res = cache.values().stream().toList();
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("S");
        System.out.println(stack.stream().collect(Collectors.joining()));
    }
}

class MergeSort implements Comparator {
    public int[] sort(int[] nums) {
        int i=0, j=nums.length-1;
        mergeSort(nums, i, j);
        return nums;
    }

    private void mergeSort(int[] nums, int i, int j) {
        if (i < j) {
            int mid = (i + j) / 2;
            mergeSort(nums, i, mid);
            mergeSort(nums, mid+1, j);
            merge(nums, i, j, mid);
        }
    }

    private void merge(int[] nums, int i, int j, int mid) {
        int[] arr = new int[j - i + 1];
        int idx1 = i, idx2 = mid+1, k = 0;

        while (idx1 <=mid && idx2 <= j) {
            if (nums[idx1] < nums[idx2]) {
                arr[k++] = nums[idx1++];
            } else {
                arr[k++] = nums[idx2++];
            }
        }
        while (idx1 <= mid) {
            arr[k++] = nums[idx1++];
        }

        while (idx2 <= j) {
            arr[k++] = nums[idx2++];
        }
        idx1 = i;
        for (int idx = 0; idx < arr.length; idx++) {
            nums[idx1++] = arr[idx];
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}

class QuickSort {
    public int[] sort(int[] nums) {
        int start = 0, end = nums.length - 1;
        quickSort(nums, start, end);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pIndex = partition(nums, start, end);
            quickSort(nums, start, pIndex - 1);
            quickSort(nums, pIndex + 1, end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pIndex = start, pivot = nums[end];

        for (int i=start; i<end; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, pIndex);
                pIndex++;
            }
        }
        swap(nums, end, pIndex);

        return pIndex;
    }

    private void swap(int[] nums, int i, int pIndex) {
        int temp = nums[i];
        nums[i] = nums[pIndex];
        nums[pIndex] = temp;
    }
}
