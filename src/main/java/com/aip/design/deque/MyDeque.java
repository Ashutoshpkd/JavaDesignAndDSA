package com.aip.design.deque;

public interface MyDeque {
    int peekFirst();
    int peekLast();

    int pollFirst();

    int pollLast();

    void offerFirst(int val);

    void offerLast(int val);

    boolean isEmpty();

    void printBucket();
}
