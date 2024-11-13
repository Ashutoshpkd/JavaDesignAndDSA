package com.aip.design.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Driver {
    public static void main(String[] args) {
        LinkedList ll = new SinglyLinkedList();
        QueueSinglyLinkedList q = new QueueSinglyLinkedList();
        ll.insertAtTail(1);
//        ll.insertAtTail(2);
        ll.insertAtTail(3);
        ll.insertAtHead(4);
        ll.insertAtHead(5);

        System.out.println("Data at index 2: " + ll.pollIndex(2));

        while (ll.size() > 0) {
            System.out.println("Data: " + ll.pollLast());
        }

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        while (!q.isEmpty()) {
            System.out.println("Queue Data: " + q.dequeue());
        }

        List<int[]> arr = new ArrayList<>();
        Queue<Long> pq = new PriorityQueue<>(Long::compare);
    }
}
