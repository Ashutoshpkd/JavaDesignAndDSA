package com;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class OK {
    private static long TIME = 0L;

    private Node head = new Node(-1, -1, -1L);
    private Node tail = new Node(-1, -1, -1L);
    private Map<Integer, Node> keyNode;
    private Queue<Node> removalQ;
    private int capacity;

    public OK(int capacity) {
        this.capacity = capacity;
        this.keyNode = new HashMap<>();
        this.removalQ = new PriorityQueue<>((n1, n2) -> {
            if (n1.counter == n2.counter) {
                return Long.compare(n1.time, n2.time);
            }
            return n1.counter - n2.counter;
        });

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if (!keyNode.containsKey(key)) return -1;
        Node resNode = keyNode.get(key);
        removalQ.remove(resNode);

        resNode.time = ++TIME;
        resNode.counter++;
        removalQ.add(resNode);

        return resNode.val;
    }

    public void put(int key, int value) {
        Node node = keyNode.getOrDefault(key, new Node(key, value, -1));
        Node removedNode = null;

        if (keyNode.containsKey(key)) {
            removalQ.remove(node);
        }

        if (capacity <= 0) {
            capacity++;
            removedNode = removalQ.poll();
            keyNode.remove(removedNode.key);
        }

        node.time = ++TIME;
        capacity--;
    }

    private static class Node {
        Node prev;
        Node next;
        int key;
        int val;
        long time;
        int counter;

        public Node(int key, int val, long time) {
            this.key = key;
            this.val = val;
            this.counter = 0;
            this.time = time;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */