package com.interview.linkedlist;

public class Driver {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.addToHead(2);
        head.addToHead(3);
        head.addToHead(4);

        head.print();
    }
}
