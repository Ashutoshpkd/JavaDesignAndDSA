package com.aip.design.linkedlist;

public class SinglyLinkedList implements LinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = new Node(-1, null);
        this.size = 0;
    }

    @Override
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        Node nextNode = this.head.next;
        this.head.next = newNode;
        newNode.next = nextNode;
        this.size++;
    }

    @Override
    public void insertAtTail(int data) {
        Node curr = this.head;
        Node prev = null;
        Node newNode = new Node(data);

        while (curr != null) {
            prev = curr;
            curr = curr.next;
        }

        prev.next = newNode;
        this.size++;
    }

    @Override
    public void insert(int data, int index) {
        Node newNode = new Node(data);
        Node curr = this.head;
        Node prev = null;

        while (index >= 0) {
            prev = curr;
            curr = curr.next;
            index--;
        }

        prev.next = newNode;
        newNode.next = curr;
        this.size++;
    }

    @Override
    public int pollFirst() {
        if (size == 0) throw new RuntimeException("Empty List");
        Node currH = this.head.next;
        int data = currH.data;
        this.head.next = currH.next;
        this.size--;
        return data;
    }

    @Override
    public int pollLast() {
        if (size == 0) throw new RuntimeException("Empty List");
        Node prev = null;
        Node curr = this.head;

        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }

        int data = curr.data;
        prev.next = null;
        this.size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int pollIndex(int index) {
        if (size == 0) throw new RuntimeException("Empty List");
        Node curr = this.head;
        Node prev = null;

        while (index >= 0) {
            prev = curr;
            curr = curr.next;
            index--;
        }

        int data = curr.data;
        prev.next = curr.next;
        this.size--;
        return data;
    }

    @Override
    public void copy(LinkedList one, LinkedList two) {

    }

    @Override
    public LinkedList merge(LinkedList one, LinkedList two) {
        Node endOne = this.head;
        Node prev = null;

        while (endOne.next != null) {
            prev = endOne;
            endOne = endOne.next;
        }

        while (two.size() > 0) {
            prev.next = new Node(two.pollFirst());
        }

        return one;
    }

    @Override
    public boolean search(int data) {
        if (size == 0) return false;
        Node curr = this.head.next;

        while (curr.next != null) {
            if (curr.data == data) return true;
            curr = curr.next;
        }

        return false;
    }

    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this(data, null);
        }

        public Node(int data, Node node) {
            this.data = data;
            this.next = node;
        }
    }
}
