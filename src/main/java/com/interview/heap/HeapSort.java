package com.interview.heap;

import java.util.Arrays;

public class HeapSort {

    public void sort(int[] nums) {
        buildMaxHeap(nums);
        System.out.println("Created max heap: " + Arrays.toString(nums));
        heapSort(nums);
        System.out.println("Array after heap sort: " + Arrays.toString(nums));
    }

    private void heapSort(int[] nums) {
        int size = nums.length;

        while (size > 0) {
            swap(nums, 0, size - 1);
            size--;
            maxHeapify(nums, size, 0);
        }
    }

    private void reverse(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private void buildMaxHeap(int[] nums) {
        for (int i=nums.length / 2 - 1; i>=0; i--) {
            maxHeapify(nums, nums.length, i);
        }
    }

    private void maxHeapify(int[] nums, int size, int idx) {
        int largest = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        if (left < size && nums[left] > nums[largest]) {
            largest = left;
        }

        if (right < size && nums[right] > nums[largest]) {
            largest = right;
        }

        if (largest != idx) {
            swap(nums, idx, largest);
            maxHeapify(nums, size, largest);
        }
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public void sortPractice(int[] nums) {
        buildMaxHeapPractice(nums);
        System.out.println("Max Heap Built: " + Arrays.toString(nums));
        sortHelperPractice(nums);
    }

    private void sortHelperPractice(int[] nums) {
        int size = nums.length;
        while (size > 0) {
            swap(nums, 0, size - 1);
            size--;
            heapifyPractice(nums, 0, size);
        }
    }

    private void buildMaxHeapPractice(int[] nums) {
        for (int i = nums.length / 2 - 1; i>=0; i--) {
            heapifyPractice(nums, i, nums.length);
        }
    }

    private void heapifyPractice(int[] nums, int idx, int size) {
        int curr = idx;
        int lChild = 2 * idx + 1;
        int rChild = 2 * idx + 2;

        if (lChild < size && nums[lChild] > nums[curr]) {
            curr = lChild;
        }

        if (rChild < size && nums[rChild] > nums[curr]) {
            curr = rChild;
        }

        if (curr != idx) {
            swap(nums, idx, curr);
            heapifyPractice(nums, curr, size);
        }
    }
}
