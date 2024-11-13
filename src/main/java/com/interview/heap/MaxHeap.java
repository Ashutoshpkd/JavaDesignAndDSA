package com.interview.heap;

import java.util.Arrays;

public class MaxHeap {

    private static final int DEFAULT_SIZE = 100;
    private int[] heap;
    private int capacity;

    private int size;

    public MaxHeap() {
        this(DEFAULT_SIZE);
    }

    public MaxHeap(int capacity) {
        this.heap = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public MaxHeap(int[] arr) {
        this.capacity = DEFAULT_SIZE;
        this.size = arr.length;
        for (int idx = this.size / 2; idx>=0; idx--) {
            heapify(arr, idx);
        }
        System.out.println(Arrays.toString(arr));
        this.heap = arr;
    }

    private int parentIdx(int idx) {
        return (idx - 1) / 2;
    }

    private int leftChildIdx(int idx) {
        return 2 * idx + 1;
    }

    private int rightChildIdx(int idx) {
        return 2 * idx + 2;
    }

    public int getSize() {
        return size;
    }

    public void insert(int val) {
        if (size == capacity) throw new RuntimeException("Max capacity reached");
        int idx = size;
        int parent = parentIdx(idx);
        size++;
        heap[idx] = val;

        while (idx >= 0 && heap[idx] > heap[parent]) {
            swap(idx, parent);
            idx = parent;
            parent = parentIdx(parent);
        }
    }

    public int peek() {
        if (size == 0) throw new RuntimeException("Empty Heap");
        return heap[0];
    }

    public int poll() {
        if (size == 0) throw new RuntimeException("Empty Heap");
        int min = heap[0];
        swap(0, size - 1);
        size--;
        heapify(0);

        return min;
    }

    public void deleteKey(int index) {

    }

    private void heapify(int idx) {
        int largest = idx;
        int left = leftChildIdx(idx);
        int right = rightChildIdx(idx);

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != idx) {
            swap(idx, largest);
            heapify(largest);
        }
    }

    private void heapify(int[] heap, int idx) {
        int largest = idx;
        int left = leftChildIdx(idx);
        int right = rightChildIdx(idx);

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != idx) {
            swap(heap, idx, largest);
            heapify(heap, largest);
        }
    }

    private void swap(int[] heap, int idx1, int idx2) {
        int temp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = temp;
    }

    private void swap(int idx1, int idx2) {
        int temp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = temp;
    }

    public void printHeap() {
        for (int i=0; i<size; i++) {
            System.out.print(this.heap[i] + ", ");
        }
    }
}
