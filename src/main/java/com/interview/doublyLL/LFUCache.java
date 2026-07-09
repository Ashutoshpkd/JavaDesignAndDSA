package com.interview.doublyLL;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    Map<Integer, Integer> keyVal;
    Map<Integer, Integer> keyFreq;
    Map<Integer, DLL> freqDLL;
    int capacity;
    int leastFreq;

    public LFUCache(int capacity) {
        this.keyVal = new HashMap<>();
        this.keyFreq = new HashMap<>();
        this.freqDLL = new HashMap<>();
        this.capacity = capacity;
        this.leastFreq = 0;
    }

    private void process(int key, int val) {
        int freq = keyFreq.get(key);

        DLL dll = freqDLL.get(freq);   // FIX 1: don't use getOrDefault
        dll.pop(key);

        // If this was the only node in leastFreq bucket
        if (freq == leastFreq && dll.len() == 0) {
            leastFreq++;
        }

        keyFreq.put(key, freq + 1);

        freqDLL.putIfAbsent(freq + 1, new DLL());
        freqDLL.get(freq + 1).push(key, val);
    }

    public int get(int key) {
        if (!keyVal.containsKey(key)) return -1;

        int val = keyVal.get(key);
        process(key, val);
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        // If key already exists
        if (keyVal.containsKey(key)) {
            keyVal.put(key, value);
            process(key, value);
            return;
        }

        // Eviction if full
        if (keyVal.size() == capacity) {
            DLL dll = freqDLL.get(leastFreq);
            Node node = dll.pop();     // remove LRU of leastFreq

            if (node != null) {
                keyVal.remove(node.key);
                keyFreq.remove(node.key);
            }
        }

        // Insert new key
        keyVal.put(key, value);
        keyFreq.put(key, 1);
        freqDLL.putIfAbsent(1, new DLL());
        freqDLL.get(1).push(key, value);

        leastFreq = 1;   // FIX 2: always reset for new insert
    }
}


class DLL {
    Map<Integer, Node> keyNode;
    Node head;
    Node tail;

    public DLL() {
        this.keyNode = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int len() {
        return keyNode.size();
    }

    public Node pop(int key) {
        if (len() == 0) return null;

        Node node = keyNode.get(key);
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        keyNode.remove(node.key);

        return node;
    }

    public Node pop() {
        if (len() == 0) return null;
        return pop(head.next.key);
    }

    public void push(int key, int val) {
        Node node = keyNode.getOrDefault(key, new Node(key, val));
        Node prevNode = tail.prev;

        prevNode.next = node;
        node.prev = prevNode;
        node.next = tail;
        tail.prev = node;

        keyNode.put(key, node);
    }
}

class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */