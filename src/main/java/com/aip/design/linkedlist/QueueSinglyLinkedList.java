package com.aip.design.linkedlist;

public class QueueSinglyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public QueueSinglyLinkedList() {
        this.head = new Node(-1);
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(int val) {
        Node newNode = new Node(val);

        if (size == 0) {
            this.head.next = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }
        this.size++;
    }

    public int dequeue() {
        if (size == 0) throw new RuntimeException("Empty Queue");
        Node next = this.head.next;
        int data = next.val;
        this.head.next = next.next;
        this.size--;
        return data;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private class Node {
        int val;
        Node next;

        public Node(int val) {
            this(val, null);
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
