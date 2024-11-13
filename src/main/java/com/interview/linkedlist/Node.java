package com.interview.linkedlist;

public class Node {
    private int val;
    private Node next;

    public Node(int val) {
        this.val = val;
    }

    public void addToHead(int val) {
        Node curr = new Node(val);
        Node itr = this;

        while (itr.next != null) {
            itr = itr.next;
        }

        itr.next = curr;
    }

    public void print() {
        Node itr = this;

        while (itr != null) {
            System.out.print(itr.val + "->");
            itr = itr.next;
        }
    }
}
