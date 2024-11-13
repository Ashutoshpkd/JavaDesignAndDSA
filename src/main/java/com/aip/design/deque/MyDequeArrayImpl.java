package com.aip.design.deque;

import java.util.Arrays;

public class MyDequeArrayImpl implements MyDeque {

    private int[] bucket;
    private int start;
    private int end;

    private final int MAX_CAPACITY;

    private int size;

    public MyDequeArrayImpl() {
        this(101);
    }

    public MyDequeArrayImpl(int initialCapacity) {
        this.bucket = new int[initialCapacity];
        this.start = (initialCapacity - 1) / 2;
        this.end = this.start;
        this.MAX_CAPACITY = initialCapacity;
        this.size = 0;
    }
    @Override
    public int peekFirst() {
        validateDeque();
        return bucket[start];
    }

    @Override
    public int peekLast() {
        validateDeque();
        return bucket[end - 1];
    }

    @Override
    public int pollFirst() {
        validateDeque();
        int res = bucket[start];
        start = (start + 1) % MAX_CAPACITY;
        this.size--;

        return res;
    }

    @Override
    public int pollLast() {
        validateDeque();
        if (end == 0) end = MAX_CAPACITY - 1;
        else end--;
        this.size--;
        return bucket[end];
    }

    @Override
    public void offerFirst(int val) {
        throwIfFull();
        int idx = start - 1;
        idx = idx < 0 ? MAX_CAPACITY - 1 : idx;
        bucket[idx] = val;
        start = idx;
        this.size++;
    }

    @Override
    public void offerLast(int val) {
        throwIfFull();
        bucket[end] = val;
        end = (end + 1) % MAX_CAPACITY;
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void validateDeque() {
        if (isEmpty()) throw new RuntimeException("Empty Deque");
    }

    private void throwIfFull() {
        if (this.size == MAX_CAPACITY) throw new RuntimeException("Max capacity reached");
    }

    @Override
    public void printBucket() {
        System.out.println(Arrays.toString(bucket));
    }
}
