package com.aip.design.linkedlist;

public interface LinkedList {
    void insertAtHead(int data);
    void insertAtTail(int data);
    void insert(int data, int index);
    int pollFirst();
    int pollLast();
    int size();
    int pollIndex(int index);
    void copy(LinkedList one, LinkedList two);
    LinkedList merge(LinkedList one, LinkedList two);
    boolean search(int data);
}
